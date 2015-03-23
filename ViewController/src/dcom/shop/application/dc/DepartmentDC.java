package dcom.shop.application.dc;

import dcom.shop.application.base.AViewObject;
import dcom.shop.application.mobile.DepartmentBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DepartmentDC extends AViewObject {
    protected static List<DepartmentBO> s_dept = new ArrayList<DepartmentBO>();


    public DepartmentDC() {
    }

    public DepartmentBO[] getDepartments() {
        DepartmentBO[] sDept = null;
        s_dept.clear();
        if (isOffline()) {
            s_dept = getCollectionFromDB(DepartmentDC.class);
            sDept = s_dept.toArray(new DepartmentBO[s_dept.size()]);
        } else {
            getFromWS();
            sDept = s_dept.toArray(new DepartmentBO[s_dept.size()]);
        }
        return sDept;
    }

    public void getFromWS() {
        RestCallerUtil restCallerUtil = new RestCallerUtil();

        String strPayload =
            "{\"x\":{\"RESTHeader\":{\"Responsibility\":\"ORDER_MGMT_SUPER_USER\",\"RespApplication\":\"ONT\",\"SecurityGroup\":\"STANDARD\",\"NLSLanguage\":\"AMERICAN\",\"Org_Id\":\"82\"},\"InputParameters\":{\"PORGCODE\":{}}}}";

        String jsonArrayAsString = restCallerUtil.invokeUPDATE(RestURI.PostGetDeptURI(), strPayload);
        if (jsonArrayAsString != null) {
            try {
                JSONParser parser = new JSONParser();
                Object object;
                object = parser.parse(jsonArrayAsString);
                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                JSONObject jsObject1 = (JSONObject) jsObject.get("XDETPS");
                JSONArray deptArray = (JSONArray) jsObject1.get("XDETPS_ITEM");
                if (deptArray != null) {
                    int size = deptArray.size();
                    //sDept = new DepartmentBO[size];
                    for (int i = 0; i < size; i++) {
                        DepartmentBO dept = new DepartmentBO();
                        JSONObject jsObject2 = (JSONObject) deptArray.get(i);
                        dept.setDeptId((jsObject2.get("DEPTID").toString()));
                        dept.setDeptName((jsObject2.get("DEPTNAME").toString()));
                        dept.setOpenJob((jsObject2.get("OPENJOB").toString()));
                        dept.setOrgCode((jsObject2.get("ORGCODE").toString()));
                        dept.setDeptDesc((jsObject2.get("DEPTDESC").toString()));
                        s_dept.add(dept);
                    }
                    super.updateSqlLiteTable(DepartmentBO.class, s_dept);
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }
}


package dcom.shop.application.dc;

import dcom.shop.application.mobile.DepartmentBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DepartmentDC {
    private List filtered_Departments = new ArrayList();
    private String deptFilter = "";
    private String nameFilter = "";
    protected static List<DepartmentBO> s_dept = new ArrayList<DepartmentBO>();


    public DepartmentDC() {
        /* try {
            GenericVirtualType payload = new GenericVirtualType(null, "payload");
            HashMap paramsMap=new HashMap();
            paramsMap.put("resAttriName","DeptDetails");
            paramsMap.put("lovDCName", "DepartmentLOV_WS");
            paramsMap.put("opeartionName", "process");
            paramsMap.put("payload",payload);
            s_dept = super.getCollection(DepartmentBO.class, paramsMap);
            filterDepartments();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } */
    }

    public DepartmentBO[] getDepartments() {
        RestCallerUtil restCallerUtil = new RestCallerUtil();
        DepartmentBO[] sDept = null;
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
                    sDept = new DepartmentBO[size];
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
                    sDept = s_dept.toArray(new DepartmentBO[s_dept.size()]);
                    return sDept;
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return sDept;
    }

    /* public void filterDepartments() {
        try {
            System.out.println("inside filter code");
            filtered_Departments.clear();

            HashMap filterFileds = new HashMap();
            filterFileds.put("DeptCode", getDeptFilter());
            filterFileds.put("Desc", getNameFilter());


            HashMap paramMap = new HashMap();
            paramMap.put("collection", deptList);
            paramMap.put("filterFieldsValues", filterFileds);
            System.out.println("called super filtered class");

            filtered_Departments = (List) super.getFileteredCollection(DepartmentBO.class, paramMap);
            System.out.println("collection size is " + filtered_Departments.size());
            providerChangeSupport.fireProviderRefresh("warehouses");
        } catch (Exception e) {
            throw new RuntimeException("My Code Error " + e);
        }
    } */
}


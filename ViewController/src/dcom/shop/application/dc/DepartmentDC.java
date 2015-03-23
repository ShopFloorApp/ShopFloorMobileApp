package dcom.shop.application.dc;

import dcom.shop.application.base.AViewObject;
import dcom.shop.application.mobile.DepartmentBO;
import dcom.shop.application.mobile.LocatorBO;
import dcom.shop.restURIDetails.RestCallerUtil;
import dcom.shop.restURIDetails.RestURI;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DepartmentDC extends AViewObject {
    protected static List<DepartmentBO> s_dept = new ArrayList<DepartmentBO>();
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private static boolean isSearched = false;

    public DepartmentDC() {
    }

    public DepartmentBO[] getDepartments() {
        DepartmentBO[] sDept = null;
        if (!isSearched) {
            s_dept.clear();
            if (isOffline()) {
                s_dept = getCollectionFromDB(DepartmentDC.class);
                sDept = s_dept.toArray(new DepartmentBO[s_dept.size()]);
            } else {
                getFromWS();
                sDept = s_dept.toArray(new DepartmentBO[s_dept.size()]);
            }
        } else {
            isSearched = false;
            return sDept = s_dept.toArray(new DepartmentBO[s_dept.size()]);
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

    public void findDepartments(String dept) {
        s_dept.clear();
        isSearched = true;
        String whereClause = "WHERE DEPTNAME LIKE \"" + dept + "%\"";
        s_dept = super.getFilteredCollectionFromDB(DepartmentBO.class, whereClause);
        providerChangeSupport.fireProviderRefresh("departments");
    }

    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }
}


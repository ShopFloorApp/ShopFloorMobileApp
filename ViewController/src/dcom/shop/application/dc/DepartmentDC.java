package dcom.shop.application.dc;

import dcom.shop.application.base.AViewObject;
import dcom.shop.application.mobile.DepartmentBO;
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
        String orgCode =
            AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcom.shop.MyWarehouse.OrgCodePG.OrgCode}").toString();
        String strPayload =
            "{\n" + "  \"x\": {\n" + "    \"RESTHeader\": {\n" +
            "      \"Responsibility\": \"ORDER_MGMT_SUPER_USER\",\n" + "      \"RespApplication\": \"ONT\",\n" +
            "      \"SecurityGroup\": \"STANDARD\",\n" + "      \"NLSLanguage\": \"AMERICAN\",\n" +
            "      \"Org_Id\": \"82\"\n" + "    },\n" + "    \"InputParameters\": {\n" + "      \"PORGCODE\":" +
            orgCode + "    }\n" + "  }\n" + "}";

        String jsonArrayAsString = restCallerUtil.invokeUPDATE(RestURI.PostGetDeptURI(), strPayload);
        if (jsonArrayAsString != null) {
            JSONObject jsObject1 = null;
            try {
                JSONParser parser = new JSONParser();
                Object object;
                object = parser.parse(jsonArrayAsString);
                JSONObject jsonObject = (JSONObject) object;
                JSONObject jsObject = (JSONObject) jsonObject.get("OutputParameters");
                jsObject1 = (JSONObject) jsObject.get("XDETPS");
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
            } catch (ClassCastException e) {
                JSONObject deptObj = (JSONObject) jsObject1.get("XDETPS_ITEM");
                if (deptObj != null) {
                    DepartmentBO dept = new DepartmentBO();
                    dept.setDeptId((deptObj.get("DEPTID").toString()));
                    dept.setDeptName((deptObj.get("DEPTNAME").toString()));
                    dept.setOpenJob((deptObj.get("OPENJOB").toString()));
                    dept.setOrgCode((deptObj.get("ORGCODE").toString()));
                    dept.setDeptDesc((deptObj.get("DEPTDESC").toString()));
                    s_dept.add(dept);
                }
                super.updateSqlLiteTable(DepartmentBO.class, s_dept);
            } catch (Exception ex) {
                ex.getMessage();
            }
        }
    }

    public void findDepartments(String dept) {
        s_dept.clear();
        isSearched = true;
        String whereClause = "WHERE DEPTNAME LIKE '" + dept + "%'";
        //String whereClause = "WHERE 1=1";
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


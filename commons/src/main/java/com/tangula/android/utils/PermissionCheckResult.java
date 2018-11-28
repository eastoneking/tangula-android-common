package com.tangula.android.utils;


import java.util.Collections;
import java.util.List;

/**
 * 授权检查结果.
 */
public class PermissionCheckResult {

    private boolean allPass;
    private List<String> passedPermissions;
    private List<String> rejectPermission;

    public PermissionCheckResult(){
        this(false, Collections.<String>emptyList(), Collections.<String>emptyList());
    }

    public PermissionCheckResult(boolean allPass, List<String> passedPermissions, List<String> rejectPermission) {
        this.allPass = allPass;
        this.passedPermissions = passedPermissions;
        this.rejectPermission = rejectPermission;
    }

    @SuppressWarnings("unused")
    public boolean isAllPass() {
        return allPass;
    }

    @SuppressWarnings("unused")
    public void setAllPass(boolean allPass) {
        this.allPass = allPass;
    }

    @SuppressWarnings("unused")
    public List<String> getPassedPermissions() {
        return passedPermissions;
    }

    @SuppressWarnings("unused")
    public void setPassedPermissions(List<String> passedPermissions) {
        this.passedPermissions = passedPermissions;
    }

    @SuppressWarnings("unused")
    public List<String> getRejectPermission() {
        return rejectPermission;
    }

    @SuppressWarnings("unused")
    public void setRejectPermission(List<String> rejectPermission) {
        this.rejectPermission = rejectPermission;
    }
}

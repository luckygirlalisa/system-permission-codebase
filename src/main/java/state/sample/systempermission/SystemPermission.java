package state.sample.systempermission;

public class SystemPermission {
    private SystemProfile profile;
    private SystemAdmin admin;
    private boolean granted;
    private boolean isUnixPermissionGranted;
    private String state;
    private PermissionState permissionState;

    public void setPermissionState(PermissionState permissionState) {
        this.permissionState = permissionState;
    }

    public SystemPermission(SystemProfile profile) {
        this.profile = profile;
        setPermissionState(PermissionState.REQUESTED);

        granted = false;
        notifyAdminOfPermissionRequest();
    }

    private void notifyAdminOfPermissionRequest() {

    }

    public void claimedBy(SystemAdmin admin) {
        if (!getState().equals(PermissionState.REQUESTED) && !getState().equals(PermissionState.UNIX_REQUESTED))
            return;
        willBeHandledBy(admin);
        if (getState().equals(PermissionState.REQUESTED)) {
            setPermissionState(PermissionState.CLAIMED);
        } else if (getState().equals(PermissionState.UNIX_REQUESTED)) {
            setPermissionState(PermissionState.UNIX_CLAIMED);
        }
    }

    private void willBeHandledBy(SystemAdmin admin) {
        this.admin = admin;
    }

    public void deniedBy(SystemAdmin admin) {
        if (!getState().equals(PermissionState.CLAIMED) && !getState().equals(PermissionState.UNIX_CLAIMED))
            return;
        if (!this.admin.equals(admin))
            return;
        granted = false;
        setPermissionState(PermissionState.DENIED);
        notifyUserOfPermissionRequestResult();    }

    private void notifyUserOfPermissionRequestResult() {

    }

    public void grantedBy(SystemAdmin admin) {
        if (!getState().equals(PermissionState.CLAIMED) && !getState().equals(PermissionState.UNIX_CLAIMED))
            return;
        if (!this.admin.equals(admin))
            return;

        if (profile.isUnixPermissionRequired() && getState().equals(PermissionState.UNIX_CLAIMED))
            isUnixPermissionGranted = true;
        else if (profile.isUnixPermissionRequired() && !profile.isUnixPermissionGranted()) {
            setPermissionState(PermissionState.UNIX_REQUESTED);
            notifyUnixAdminsOfPermissionRequest();
            return;
        }
        setPermissionState(PermissionState.GRANTED);
        granted = true;
        notifyUserOfPermissionRequestResult();
    }

    private void notifyUnixAdminsOfPermissionRequest() {

    }

    public boolean isGranted() {
        return this.granted;
    }

    public PermissionState getState() {
        return permissionState;
    }
}

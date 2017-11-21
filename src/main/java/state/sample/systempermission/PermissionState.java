package state.sample.systempermission;

public class PermissionState {

    public final static PermissionState REQUESTED = new PermissionState("REQUESTED");
    public final static PermissionState CLAIMED = new PermissionState("CLAIMED");
    public final static PermissionState GRANTED = new PermissionState("GRANTED");
    public final static PermissionState DENIED = new PermissionState("DENIED");
    public static final PermissionState UNIX_REQUESTED = new PermissionState("UNIX_REQUESTED");
    public static final PermissionState UNIX_CLAIMED = new PermissionState("UNIX_CLAIMED");
    private String stateName;

    public PermissionState(String stateName) {

        this.stateName = stateName;
    }

    @Override
    public String toString() {
        return stateName;
    }
}

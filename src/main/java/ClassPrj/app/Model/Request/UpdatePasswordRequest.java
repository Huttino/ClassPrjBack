package ClassPrj.app.Model.Request;

public class UpdatePasswordRequest {
    private String OldPassword;
    private String newPassword;
    private String confirmNewPassword;

    public UpdatePasswordRequest(String oldPassword, String newPassword, String confirmNewPassword) {
        OldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String oldPassword) {
        OldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public boolean confirmPassword(){
        return newPassword.equals(confirmNewPassword);
    }
}

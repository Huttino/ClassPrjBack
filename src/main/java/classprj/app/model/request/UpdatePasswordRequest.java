package classprj.app.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdatePasswordRequest {

    @NotNull
    @NotEmpty
    private String oldPassword;
    @NotNull
    @NotEmpty
    private String newPassword;
    @NotNull
    @NotEmpty
    private String confirmNewPassword;

    public UpdatePasswordRequest(
            String oldPassword,
            String newPassword,
            String confirmNewPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        oldPassword = oldPassword;
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

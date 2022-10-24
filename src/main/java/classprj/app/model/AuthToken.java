package classprj.app.model;

public class AuthToken {
    private String accessToken;
    private String type = "Bearer";
    private String refreshToken;

    public AuthToken() {
    }

    public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public AuthToken(String token) {
        this.accessToken = token;
    }


    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

	public AuthToken(String accessToken, String refreshToken) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	@Override
	public String toString() {
		return "AuthToken [accesstoken=" + accessToken + ", type=" + type + ", refreshToken=" + refreshToken + "]";
	}

    
   }



package ceni.endra.backend.service;

import ceni.endra.backend.beans.Login;

public interface LoginService {
	
	int newLoginAccount(Login request);
	int updateCredentials(Login request);
	int deleteAccount(Login request);

}

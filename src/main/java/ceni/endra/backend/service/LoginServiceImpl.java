package ceni.endra.backend.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceni.endra.backend.beans.Login;
import ceni.endra.backend.beans.Travel;
import ceni.endra.backend.repository.LoginDao;

@Service("loginServiceImpl")
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginDao loginDao;

	@Override
	public int newLoginAccount(Login request) {
		
		Login newAcc = new Login();
		newAcc.setIdUser(request.getIdUser());
		newAcc.setUsername(request.getUsername());
		newAcc.setPassword(request.getPassword());
		newAcc.setTravelAgent(request.getTravelAgent());
		
		if(loginDao.saveAndFlush(newAcc).getIdUser() == 0  || loginDao.saveAndFlush(newAcc).getIdUser() == null) {
			return 0;
		}
		return 1;
		
	}

	@Override
	public int updateCredentials(Login request) {
		try {
			Login newAcc = loginDao.findById(request.getIdUser()).get();
			if(newAcc != null) {
				newAcc.setUsername(request.getUsername());
				newAcc.setPassword(request.getPassword());
				
				loginDao.saveAndFlush(newAcc);
			}
			else {
				return 0;
			}
		}
		catch(NoSuchElementException ex) {
			
			return 0;
			
		}
		return 1;
	}

	@Override
	public int deleteAccount(Login request) {
		
		try {
			loginDao.delete(loginDao.findById(request.getIdUser()).get());
		}
		catch(IllegalArgumentException ex) {
			
			return 0;
		}
		return 1;

	}

}

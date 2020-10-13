package io.github.dagurasu.corp.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import io.github.dagurasu.corp.model.TokenUser;
import io.github.dagurasu.corp.model.User;


public class TokenDao {

	private static Map<TokenUser, User> USER = new LinkedHashMap<>();

	public TokenDao() {
		userInMAp();
	}
	
	public boolean isValid(TokenUser user) {
		return USER.containsKey(user);
	}
	
	private void userInMAp() {
		USER.put(new TokenUser("AAA", parseDate("31/12/2015")), new User.Builder().comNome("Douglas").comLogin("doug").comSenha("pass").build());
		USER.put(new TokenUser("BBB", parseDate("31/12/2015")), new User.Builder().comNome("Eliot").comLogin("eliot").comSenha("pass").build());
		USER.put(new TokenUser("CCC", parseDate("31/12/2015")), new User.Builder().comNome("Walter").comLogin("walter").comSenha("pass").build());
		USER.put(new TokenUser("DDD", parseDate("31/12/2015")), new User.Builder().comNome("Barry").comLogin("barry").comSenha("pass").build());
	}


	private Date parseDate(String stringData) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(stringData);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}



}

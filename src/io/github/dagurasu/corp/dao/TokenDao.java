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
		USER.put(new TokenUser("AAA", parseDate("13/10/2020")), new User.Builder().name("Douglas").login("doug").password("pass").build());
		USER.put(new TokenUser("BBB", parseDate("13/10/2020")), new User.Builder().name("Eliot").login("eliot").password("pass").build());
		USER.put(new TokenUser("CCC", parseDate("13/10/2020")), new User.Builder().name("Walter").login("walter").password("pass").build());
		USER.put(new TokenUser("DDD", parseDate("13/10/2020")), new User.Builder().name("Barry").login("barry").password("pass").build());
	}


	private Date parseDate(String stringData) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(stringData);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}



}

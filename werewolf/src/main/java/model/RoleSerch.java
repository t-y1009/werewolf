package model;

public class RoleSerch {
	public String confirmRoleName(Player fortuneResultPlayer) {
		String roleName = null;
		switch(fortuneResultPlayer.getRole()) {
			case 1:
			case 5:
				roleName = "村人";
				break;
			
			case 2:
				roleName = "人狼";
				break;
				
			case 3:
				roleName= "占師";
				break;
			
			case 4:
				roleName = "騎士";
				break;
		}
		return roleName;
	}
}

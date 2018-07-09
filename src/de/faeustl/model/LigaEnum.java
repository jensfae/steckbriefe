package de.faeustl.model;

//public class EnumTest4
//{
//   public static void main( String[] args )
//   {
//      double a = 4, b = 5;
//      for( LigaEnum f : LigaEnum.values() )
//         System.out.println( f.getWpNummer() + " " + f.values());
//   
//   
//      System.out.println(LigaEnum.valueOf("LLF").getWpNummer());
//      System.out.println(LigaEnum.valueOf("LigaSüd").getWpNummer()); 
//      System.out.println(LigaEnum.valueOf("LigaSüd")); 
//   }
//   
//  
//}


public  enum LigaEnum {
	
	 LigaSüd ("1", "3. Liga Süd"),
	 LLF ("38", "Landesliga Staffel N"),
	 BZKF ("297", "Landesliga Staffel N"),
	 
	 //männer
	 BZOLM("68","Landesliga Staffel N"),
	 BZLM("292","Landesliga Staffel N"),
	 
	 //weibliche Jugend
	 
	 BYWB("133", "Landesliga Staffel N"),
	 LLWC("296", "Landesliga Staffel N"),
	 
	 //männlische Jugen
	 LLMA("295", "Landesliga Staffel N"),
	 LLMB("236", "Landesliga Staffel N"),
	 ÜBOLMC("126", "Landesliga Staffel N");
	
	private final String wpNummer;
	private final String liganame;
	
	LigaEnum(String ligaNummer, String pLigaName){
		this.wpNummer = ligaNummer;
		this.liganame = pLigaName;
	}

	public String getLiganame() {
		return liganame;
	}

	public String getWpNummer(String pLigaName) {
		if (liganame.equals(pLigaName))
		return wpNummer;
		else return null;
	}
}

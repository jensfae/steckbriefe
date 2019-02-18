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
	
//	 LigaSüd ("56", "3. Liga Süd"),
	 LLF ("38", "Landesliga Staffel N");
//	 BZKF ("297", "Landesliga Staffel N");
	 
	 //männer
//	 BZOLM("68","Bezirksoberliga");
//	 BZLM("292","Landesliga Staffel N"),
	 
	 //weibliche Jugend
	 
//	 BYWB("133", "Bayernliga");
//	 LLWC("296", "Landesliga Nord");
	 
	 //männlische Jugen
//	 LLMA("295", "Landesliga Süd");
//	 LLMB("236", "Landesliga Nord");
//	 ÜBOLMC("126", "ÜBOL Ost");
	
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

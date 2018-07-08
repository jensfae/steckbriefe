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
	
	 LigaSüd ("1"),
	 LLF ("38"),
	 BZKF ("297"),
	 
	 //männer
	 BZOLM("68"),
	 BZLM("292"),
	 
	 //weibliche Jugend
	 
	 BYWB("133"),
	 LLWC("296"),
	 
	 //männlische Jugen
	 LLMA("295"),
	 LLMB("236"),
	 ÜBOLMC("126");
	
	private final String wpNummer;
	
	LigaEnum(String ligaNummer){
		this.wpNummer = ligaNummer;
	}

	public String getWpNummer() {
		return wpNummer;
	}
}

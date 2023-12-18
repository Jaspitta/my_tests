//package com.example.demo.utils;
//
//public class Archived {
//
//
////	static boolean checkDate(String date) throws ParseException {
////		Date dateFromString = new SimpleDateFormat("yyyy-MM-dd").parse(date);
////		Calendar birthDate = Calendar.getInstance();
////		birthDate.setTime(dateFromString);
////		birthDate.add(Calendar.YEAR, 18);
////		Calendar currentDate = Calendar.getInstance();
////		currentDate.setTimeInMillis(System.currentTimeMillis());
////		return birthDate.before(currentDate);
////	}
//
//  /*
//   *
//   * 16 caratter alfanumerici
//   * 3 lett cognome
//   * 3 lett nome
//   * 2 num anno nascita
//   * 1 lett mese nascita
//   * 2 num giorno nascita e sesso
//   * 4 = 1 lett + 3 num per comune nascita o stato estero
//   * 1 carattere controllo
//   *
//   *
//   * */
////	public static String buildCodiceFiscale(String surname){
////
////		if(surname == null || surname.isBlank()){
////			log.error("Missing mandatory param");
////			return null;
////		}
////
////
////
////	}
//
////	private static String getSurnameCode(String surname){
////
////		for(int i=0;i<surname.length();i++)
////			if(isVocal(surname.charAt(i)))
////				vocali_COGNOME=vocali_COGNOME+surname.charAt(i);
////			else
////			if(surname.charAt(i)!=' ')
////				consonanti_COGNOME=consonanti_COGNOME+surname.charAt(i);
////
////
////		String s="";
////		if(consonanti_COGNOME.length()>=3){
////			for(int i=0;i<3;i++)
////				s=s+consonanti_COGNOME.charAt(i);
////			return s;
////		}
////		if(consonanti_COGNOME.length()==2){
////			s=s+consonanti_COGNOME.charAt(0)+consonanti_COGNOME.charAt(1)+vocali_COGNOME.charAt(0);
////			return s;
////		}
////		if(consonanti_COGNOME.length()==1){
////			s=s+consonanti_COGNOME.charAt(0)+vocali_COGNOME.charAt(0)+vocali_COGNOME.charAt(1);
////			return s;
////		}
////		else{
////			for(int i=0;i<3;i++)
////				s=s+vocali_COGNOME.charAt(i);
////			return s;
////		}
////	}
//
//
////		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
////		String numbers = "0123456789";
////		String symbols = "~`!@#$%^&*()-_=+[{]}\\\\|;:\\'\\\",<.>/?";
////		String allCharacters = characters + numbers + symbols;
////
////		for(int i = 0; i < 10000; i++) {
////			synchronized("LOCK"){
////				char[] pwd = RandomStringUtils.random(15, 0, allCharacters.length(), false, false, allCharacters.toCharArray()).toCharArray();
////				List<Integer> arrayExceptions = new ArrayList<>();
////				System.out.println("pass before: " + String.valueOf(pwd));
////				pwd[randomRangeExcept(0, 15, arrayExceptions)] = characters.toCharArray()[RandomUtils.nextInt(0, characters.length())];
////				pwd[randomRangeExcept(0, 15, arrayExceptions)] = numbers.toCharArray()[RandomUtils.nextInt(0, numbers.length())];
////				pwd[randomRangeExcept(0, 15, arrayExceptions)] = symbols.toCharArray()[RandomUtils.nextInt(0, symbols.length())];
////				System.out.println("pass after: " + String.valueOf(pwd));
////				boolean foundDigit = false;
////				boolean foundChar = false;
////				boolean foundSpecial = false;
////				for(int j = 0 ; j< pwd.length ; j++){
////					if(Character.isDigit(pwd[j])) {
////						foundDigit = true;
////					}
////						if(Character.isAlphabetic(pwd[j])) {
////							foundChar = true;
////						}
////					if(symbols.contains(String.valueOf(pwd[j]))){
////					foundSpecial = true;
////				}
////			}
////				if (!(foundDigit && foundChar && foundSpecial)) {
////					System.out.println("Ops");
////				}
////		}
//
////		String statusAsJson = "" +
////			"{" +
////			"\"status\":\"somesome\"" +
////			"}";
////
////
////		Foo foo = new Foo();
////
////		HttpEntity<Foo> entity = new HttpEntity<>(foo, null);
////
////
////		System.out.println(entity.getBody());
////		System.out.println("CWD=" + new File(".").getAbsolutePath());
////		try {
////			System.out.println(Files.readAllLines(Paths.get("src/main/resources/testing.txt")));
////		} catch (IOException e) {
////			throw new RuntimeException(e);
////		}
////
////		Map<String, Map<String, Set<String>>> localAuthority = Map.of(
////			"ITALIA",
////			Map.of(
////				"Torino",
////				Set.of(
////					"comune1",
////					"comune2"
////				),
////				"Provincia2",
////				Set.of(
////					"comune1",
////					"comune2"
////				)
////			)
////		);
//
//
////	 static private int randomRangeExcept(int min, int max, List<Integer> except){
////		 Random rand = new Random();
////		 int number;
////		do {
////			number = rand.nextInt(max) + min;
////		} while (except.contains(number));
////		except.add(number);
////		return number;
////	}
////
////
////	@Data
////	public static class Foo{
////
////		String bar = "bar";
////
////		Foo(){
////			this.bar = "bar";
////		}
////
////	}
////
////	private static Map<String, BiFunction<Object, Map, String>> validator(){
////		return Map.ofEntries(
////			Map.entry("cognitoId", (v, map) -> null),
////			Map.entry("name", (v, m) ->
////				v == null || String.valueOf(v).isBlank() ?
////					"name is a mandatory field"
////					:
////					null
////			),
////			Map.entry("role", (v, m) -> {
////				if (v != null || String.valueOf(v).isBlank()){
////				}
////				return null;
////			}),
////			Map.entry("country", (v, m) -> {
////				if (v == null || String.valueOf(v).isBlank()) return "Country is a mandatory field";
////				LocalAuthorityMap localAuctority = new LocalAuthorityMap();
////				if (true) return "Country provided is not valid";
////				return null;
////			})
////		);
////	}
//
//
//  //		Map<String, PublicationStatus> convertedList = List.of("1", "2", "3", "4", "5")
////			.stream().collect(Collectors.toMap(
////				Function.identity(), (x) -> PublicationStatus.PUBLISHED
////			));
////
////		System.out.println(convertedList);
////		System.out.println(checkDate("2022-01-01"));
////		System.out.println(checkDate("2000-01-01"));
////		System.out.println(checkDate("1980-01-01"));
////		System.out.println(checkDate("2015-01-01"));
////
//
//
////		System.out.println(LocalAuthorityMap.validateCombinationGreedy("","",""));													//false
////		System.out.println(LocalAuthorityMap.validateCombinationGreedy("ITALIA","TORINO","TORINO"));				//true
////		System.out.println(LocalAuthorityMap.validateCombinationGreedy("FRANCIA","ESTERO","ESTERO"));			//true
////		System.out.println(LocalAuthorityMap.validateCombinationGreedy("ITaliA","TOriNo","TOrIno"));				//true
////		System.out.println(LocalAuthorityMap.validateCombinationGreedy("GERMAniA","eSTero","ESTEro"));			//true
////		System.out.println(LocalAuthorityMap.validateCombinationGreedy("dasda","dasdas","dassda"));				//false
////		System.out.println(LocalAuthorityMap.validateCombinationGreedy("ITALIA","ESTERO","TORINO"));				//false
////		System.out.println(LocalAuthorityMap.validateCombinationGreedy("ITALIA",null,"TORINO"));						//false
////		System.out.println(LocalAuthorityMap.validateCombinationGreedy("ITALIA","TORINO","AlA Di STurA"));	//true
//
//
//
//}

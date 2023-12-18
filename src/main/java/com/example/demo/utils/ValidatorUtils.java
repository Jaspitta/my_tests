package com.example.demo.utils;

import java.util.Calendar;

public abstract class ValidatorUtils {

  public static String concatenateStrings(String separator, String... strings){
    return String.join(separator, strings);
  }

  public static Boolean validateEmail(String email){

    return email != null && email.toLowerCase().matches(
      "(?:[a-z0-9!#$%&'*+=?^_`\\{|\\}~-]+(?:\\.[a-z0-9!#$%&'*+=?^_`\\{|\\}~-]+)*|\\\"(?:"
        + "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\\\")@(?:(?:"
        + "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:"
        + "(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:"
        + "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
    );
  }
  public static Boolean isValidUrl(String url){
    return url != null && url.matches(
            "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"
    );
  }

  public static Boolean validatePhoneNumber(String phoneNumber){

    return phoneNumber != null && phoneNumber.toLowerCase().matches(
      "^[+]{1}(?:[0-9\\-\\(\\)\\/\\.]\\s?){6,15}[0-9]{1}$"
    );

  }

  public static Boolean validateCompanyUCode(String companyUCode){

    return companyUCode != null && companyUCode.toLowerCase().matches(
      "^[a-zA-Z0-9]{3}$"
    );

  }
  public static Boolean checkPhoneNumberLoosly(String phoneNumber){

    return phoneNumber != null && phoneNumber.toLowerCase().matches(
      "^([+-]?[0-9]){5,20}$"
    );

  }

  public static Boolean validateLink(String link){
    return link != null && link.toLowerCase().matches(
      "^(https?:\\/\\/)?www\\."
    );
  }

  public static Boolean validateCF(String cf){
    if(cf == null || cf.isBlank() || cf.length() != 16)
      return false;

    cf = cf.toUpperCase();

    int sum = 0;
    for(int i = 0 ; i < cf.length() - 1 ; i++) {
      if(( i + 1 ) % 2 != 0){
        switch(String.valueOf(cf.charAt(i))){
          case "0": case "A": sum = sum + 1;
            break;
          case "1": case "B":
            break;
          case "2": case "C": sum = sum + 5;
            break;
          case "3": case "D": sum = sum + 7;
            break;
          case "4": case "E": sum = sum + 9;
            break;
          case "5": case "F": sum = sum + 13;
            break;
          case "6": case "G": sum = sum + 15;
            break;
          case "7": case "H": sum = sum + 17;
            break;
          case "8": case "I": sum = sum + 19;
            break;
          case "9": case "J": sum = sum + 21;
            break;
          case "K": sum = sum + 2;
            break;
          case "L": sum = sum + 4;
            break;
          case "M": sum = sum + 18;
            break;
          case "N": sum = sum + 20;
            break;
          case "O": sum = sum + 11;
            break;
          case "P": sum = sum + 3;
            break;
          case "Q": sum = sum + 6;
            break;
          case "R": sum = sum + 8;
            break;
          case "S": sum = sum + 12;
            break;
          case "T": sum = sum + 14;
            break;
          case "U": sum = sum + 16;
            break;
          case "V": sum = sum + 10;
            break;
          case "W": sum = sum + 22;
            break;
          case "X": sum = sum + 25;
            break;
          case "Y": sum = sum + 24;
            break;
          case "Z": sum = sum + 23;
            break;
        }
      } else {
        switch(String.valueOf(cf.charAt(i))){
          case "0": case "A":
            break;
          case "1": case "B": sum = sum + 1;
            break;
          case "2": case "C": sum = sum + 2;
            break;
          case "3": case "D": sum = sum + 3;
            break;
          case "4": case "E": sum = sum + 4;
            break;
          case "5": case "F": sum = sum + 5;
            break;
          case "6": case "G": sum = sum + 6;
            break;
          case "7": case "H": sum = sum + 7;
            break;
          case "8": case "I": sum = sum + 8;
            break;
          case "9": case "J": sum = sum + 9;
            break;
          case "K": sum = sum + 10;
            break;
          case "L": sum = sum + 11;
            break;
          case "M": sum = sum + 12;
            break;
          case "N": sum = sum + 13;
            break;
          case "O": sum = sum + 14;
            break;
          case "P": sum = sum + 15;
            break;
          case "Q": sum = sum + 16;
            break;
          case "R": sum = sum + 17;
            break;
          case "S": sum = sum + 18;
            break;
          case "T": sum = sum + 19;
            break;
          case "U": sum = sum + 20;
            break;
          case "V": sum = sum + 21;
            break;
          case "W": sum = sum + 22;
            break;
          case "X": sum = sum + 23;
            break;
          case "Y": sum = sum + 24;
            break;
          case "Z": sum = sum + 25;
            break;
        }
      }
    }

    return String.valueOf((char) ((sum % 26) + 65))
      .equals(String.valueOf(cf.charAt(15)));
  }

  public static Boolean validateBirthDate(Calendar birthDate){
    if(birthDate == null) return false;
    birthDate.add(Calendar.YEAR, 18);
    Calendar currDate = Calendar.getInstance();
    currDate.setTimeInMillis(System.currentTimeMillis());
    return birthDate.before(currDate);
  }

}

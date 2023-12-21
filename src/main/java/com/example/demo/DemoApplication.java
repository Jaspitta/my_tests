package com.example.demo;

import com.example.demo.tests.InternalLoggingAnnotations;
import com.example.demo.tests.ValidationAnnotations;
import com.example.demo.utils.RESTClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Map;

@SpringBootApplication
@Log4j2
@RestController
public class DemoApplication {

    // private static String refuseBody = "<head>\n" +
    // " <style>\n" +
    // " body {\n" +
    // " font-family: Arial, sans-serif;\n" +
    // " padding: 20px;\n" +
    // " }\n" +
    // " .banner-table {\n" +
    // " width: 100%;\n" +
    // " }\n" +
    // " .banner-table img {\n" +
    // " max-width: 100%;\n" +
    // " display: block;\n" +
    // " }\n" +
    // " .signature-table {\n" +
    // " width: 100%;\n" +
    // " text-align: center;\n" +
    // " display: flex;\n" +
    // " justify-content: flex-end;\n" +
    // " }\n" +
    // " .signature-table th, .signature-table td {\n" +
    // " padding: 10px;\n" +
    // " }\n" +
    // " </style>\n" +
    // "</head>\n" +
    // "<body>\n" +
    // "\t<div>\n" +
    // " <!----- banner ----->\n" +
    // " <table class=\"banner-table\">\n" +
    // " <tr>\n" +
    // " <td><img alt=\"banner-img-gse\" height=\"100\" title=\"GSE\"
    // src=\"https://upload.wikimedia.org/wikipedia/it/a/a4/Logo_GSE.png\"
    // align=\"left\" /></td>\n" +
    // " <td><img alt=\"banner-img-min\" height=\"150\" title=\"MIN\"
    // src=\"https://www.mase.gov.it/sites/default/files/styles/media_home_559/public/Logo_MASE_8.jpg?itok=htpp_mAI\"
    // align=\"right\" /></td>\n" +
    // " </tr>\n" +
    // " </table>\n" +
    // " <!----- body ----->\n" +
    // " <div class=\"body-text\">\n" +
    // "\t\t\t<p><b>DA: </b>${fromAddress}<br></p>\n" +
    // "\t\t\t<p><b>A: </b>${toAddress}</br></p>\n" +
    // "\t\t\t</br></br>\n" +
    // "\t\t\t<p><b>Oggetto: </b>Richiesta di registrazione sulla Piattaforma Unica
    // Nazionale (PUN) previsto dal Decreto Ministeriale n. 106 del 16/03/2023 della
    // società ${registrationCompanyName}</p>\n" +
    // "\t\t\t</br>\n" +
    // "\t\t\t<p>Gentile operatore,</br>con la presente si comunica che la sua
    // richiesta di registrazione alla Piattaforma Unica Nazionale (PUN) presentata
    // in data ${registrationRequestDate}, ora ${registrationRequestTime} non e'
    // stata accettata</p>\n" +
    // "\t\t\t<p>La sua richiesta non e' stata accettata per il seguente motivo:" +
    // "\t\t\t</br><ul><li>${reasonRefuse}</li></ul></p>\n" +
    // "\t\t\t<p>Distinti saluti.</p>\n" +
    // " </div>\n" +
    // "\n" +
    // " <!----- signature ----->\n" +
    // " <table class=\"signature-table\">\n" +
    // " <tr><th>Il Direttore</br>Gennaro Niglio</th></tr>\n" +
    // " <tr>\n" +
    // " <td>Firma autografa sostituita a mezzo\n" +
    // " </br>stampa, ai sensi e per gli effetti dell'art.3,\n" +
    // " </br>comma 2 del d.lgs 39/93, convalidata\n" +
    // " </br>digitalmente</td>\n" +
    // " </tr>\n" +
    // " </table>\n" +
    // "\t\t\n" +
    // "\t\t<!----- footer ----->\n" +
    // "\t\t<p>Gestore dei Servizi Energetici - GSE S.p.A.</p>\n" +
    // "\t\t<p><small>Socio Unico Ministero dell'Economia e delle Finanze D. Lgs
    // 79/99 - Sede Legale: 00197 Roma, V.le Maresciallo Pilsudski, 92\n" +
    // "\t\t</br>Reg. Imprese di Roma, P.IVA e C.F. 05754381001 - R.E.A. di Roma n.
    // 918934 - Cap. Soc. € 26.000.000,00 i.v.\n" +
    // "\t\t</br>Tel. +39.0680111 - info@gse.it - www.gse.it - Numero Verde:
    // 800.16.16.16</small></p>\n" +
    // " </div>\n" +
    // "</body>";
    //
    // static final Set<String> SUCCESFULL_USERS = Set.of(
    // "user1",
    // "user3",
    // "user7",
    // "user10"
    // );
    //
    // static final Set<String> users = IntStream.range(0, 11)
    // .mapToObj((num) -> "user" + num)
    // .collect(Collectors.toSet());

    public static void main(String[] args) throws UnsupportedEncodingException, JsonProcessingException {

        SpringApplication.run(DemoApplication.class, args);

        var test = InternalLoggingAnnotations.builder().build();

        test.test("String passed as param");

    }


    // static Boolean checkUserAllowed(String username){
    // if(username == null || username.isBlank()) return null;
    //
    // List<Integer> list = IntStream.range(0,
    // 11).boxed().collect(Collectors.toList());
    //
    // list.forEach(i ->{
    // System.out.println(i);
    // return;
    // });
    //
    //
    //
    // return SUCCESFULL_USERS.contains(username);
    // }

    // static private String cleanUpReasonString(String reason){
    // if(reason == null) return null;
    // return reason
    // .replace("\"","")
    // .replace("\\n","<br>");
    // }

    @GetMapping(value = {"/called"})
    public ResponseEntity<?> called() {

        return new ResponseEntity<>("This is the error message", HttpStatus.CONFLICT);

    }

    @PostMapping(value = {"/testId"})
    public ResponseEntity<?> testId(@RequestBody Map input) {

        var converted = new ObjectMapper().convertValue(input, FooInternal.class);
        System.out.println(converted);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(value = {"/caller"})
    public ResponseEntity<?> caller() throws URISyntaxException {
        ResponseEntity<Object> resp;
        try {

            resp = new RESTClient<>(null, "http://127.0.0.1:8080/called", null, null, Object.class)
                    .executeGET();
        } catch (HttpClientErrorException ex) {
            resp = new ResponseEntity<>(ex.getResponseBodyAsString(), HttpStatus.valueOf(ex.getRawStatusCode()));
        }
        return resp;

    }

}

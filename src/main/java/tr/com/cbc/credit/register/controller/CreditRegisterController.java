package tr.com.cbc.credit.register.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tr.com.cbc.credit.register.CreditRegister;

import tr.com.cbc.credit.register.service.Validation;

@RestController
public class CreditRegisterController {

	@Autowired
	private Validation validation;

	@RequestMapping(value = "/credit/save", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")

	public ResponseEntity<?> getKredi(@RequestBody CreditRegister creditregister)
			throws IOException, NumberFormatException

	{

		try {

			validation.krediNumarasiKontrol(creditregister.getKrediNumarasi());
			validation.bankadiKontrol(creditregister.getBankaadi());
			validation.krediTuruKontrol(creditregister.getKrediTuru());
			validation.tcknKontrol((creditregister.getTckn()));
				try
			{
				validation.tutarKontrol(creditregister.getTutar());
			}
		catch( NumberFormatException e )
			{
			   e.printStackTrace();
			   throw new  ValidationException("Tutar nümerik olmalıdır");
			}

			
			//	validation.isAllNumeric(creditregister.getTutar());
			

			validation.dosyaKontrol(creditregister.getKrediNumarasi(), creditregister.getTckn(),
					creditregister.getBankaadi(), creditregister.getTutar(), creditregister.getKrediTuru());

			return ResponseEntity.ok("başarılı : ");
		} catch (ValidationException e) {
			return ResponseEntity.badRequest().body("başarısız : \n" + "hata mesajı : " + e.getMessage());
		}

	}

}

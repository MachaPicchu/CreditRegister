package tr.com.cbc.credit.register.service;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ValidationException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Validation {

	// dosyaKontrol(krediNumarasi, tckn, bankaadi, tutar, krediTuru);

	public void bankadiKontrol(String bankaadi) throws ValidationException {

		if (bankaadi == null || bankaadi.isEmpty()) {
			throw new ValidationException("banka adi bos olamaz");
		}

		if (!bankaadi.equals("Akbank") && !bankaadi.equals("Halkbank") && !bankaadi.equals("Ziraat")
				&& !bankaadi.equals("Garanti") && !bankaadi.equals("Finansbank")) {
			throw new ValidationException("banka adi tanimli degil");

		}

	}

	public void krediTuruKontrol(String kredituru) {

		if (kredituru == null || kredituru.isEmpty()) {
			throw new ValidationException("KrediTuru  bos olamaz");
		}

		if (!kredituru.equals("Konut Kredisi") && !kredituru.equals("Taşıt Kredisi")
				&& !kredituru.equals("İhtiyaç Kredisi")) {
			throw new ValidationException("kredi Turu tanimli degil");

		}

	}

	public void tcknKontrol(String tckn) throws ValidationException {

		if (tckn == null || tckn.isEmpty()) {
			throw new ValidationException("tckn bos olamaz");
		}

		if (tckn.length() != 11)
			throw new ValidationException("Geçersiz  TCKN");

		char[] chars = tckn.toCharArray();
		int[] a = new int[11];

		for (int i = 0; i < 11; i++) {
			a[i] = chars[i] - '0';
		}

		if (a[0] == 0)
			throw new ValidationException("Geçersiz  TCKN");
		if (a[10] % 2 == 1)
			throw new ValidationException("Geçersiz  TCKN");

		if (((a[0] + a[2] + a[4] + a[6] + a[8]) * 7 - (a[1] + a[3] + a[5] + a[7])) % 10 != a[9])
			throw new ValidationException("Geçersiz  TCKN");

		if ((a[0] + a[1] + a[2] + a[3] + a[4] + a[5] + a[6] + a[7] + a[8] + a[9]) % 10 != a[10])
			throw new ValidationException("Geçersiz  TCKN");

	}

	public void tutarKontrol(String tutar) throws ValidationException  {

		if (tutar == null || tutar.isEmpty()) {
			throw new ValidationException("Tutar bos olamaz");
		}
		int tutarValid = Integer.valueOf(tutar);

		if (1000 > tutarValid || tutarValid > 999999) {

			throw new ValidationException("Geçersiz Tutar");

		}
//		if(!isAllNumeric(tutar))
//		{
//			throw new  ValidationException("Tutar nümerik olmalıdır");
//		}



	}

	public void krediNumarasiKontrol(String kredinumarasi) throws ValidationException {

		if (kredinumarasi == null || kredinumarasi.isEmpty()) {
			throw new ValidationException("Kredi Numarasi bos olamaz");
		}

	}

	public void dosyaKontrol(String krediNumarasi, String tckn, String bankaadi, String tutar, String krediTuru)
			throws ValidationException, IOException {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		File file = new File("C:\\Users\\svsal\\Desktop\\KrediDosyaları\\" + dateFormat.format(date) + "_krediler.txt");

		try {
			FileWriter fileWriter;
			fileWriter = new FileWriter(file, true);
			BufferedWriter bWriter = new BufferedWriter(fileWriter);
			BufferedReader reader = null;
			reader = new BufferedReader(new FileReader(file));
			String satir = reader.readLine();
			int varMı = 0;
			if (satir == null) {
				bWriter.write(krediNumarasi + ";" + tckn + ";" + bankaadi + ";" + tutar + ";" + krediTuru);
				bWriter.newLine();

				bWriter.close();
			}

			else {
				while (satir != null) {
					String[] arrOfStr = satir.split(";", 5);

					if (arrOfStr[0].equals(krediNumarasi) && arrOfStr[2].equals(bankaadi)) {
						

						varMı++;
						break;
					}

					satir = reader.readLine();
				}

				if (varMı == 0) {
					bWriter.write(krediNumarasi + ";" + tckn + ";" + bankaadi + ";" + tutar + ";" + krediTuru);
					bWriter.newLine();

					bWriter.close();
				} else {
					throw new ValidationException(
							"Aynı gün içerisinde aynı banka adı ve kredi numarası ile 1'den fazla kayıt yapılamaz");
				}

			}

		}

		catch (ValidationException e) {

			e.printStackTrace();
			throw e;

		}

	}

	

//	public boolean  isAllNumeric(String code)  throws ValidationException{
//		
//		try {
//			
//			for (int i = 0; i < code.length(); i++) 
//				if (!Character.isDigit(code.charAt(i)))
//				{
//					throw new  ValidationException("Tutar nümerik olmalıdır");
//				}
//	
//			
//					
//		}
//		catch(NumberFormatException e)
//		{
//	       e.printStackTrace();
//			throw e ;
//		}
//		
//		return true;
//
//	}
//	
	
	
//	public void isAllNumeric(String str)
//	{
//	    for (char c : str.toCharArray())
//	    {
//	        if (!Character.isDigit(c)) 
//	        {
//	     //   	throw new NumberFormatException("Tutar nümerik olmalıdır");
//	        }
//	    }
//	
//	}

}


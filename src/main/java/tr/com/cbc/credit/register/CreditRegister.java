package tr.com.cbc.credit.register;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ValidationException;

import org.w3c.dom.ranges.RangeException;

public class CreditRegister {
	private String krediNumarasi;
	private String tckn;
	private String bankaadi;
	private String tutar;
	private String krediTuru;
	
	public CreditRegister()
	{
		
	}
	
	public CreditRegister(String krediNumarasi, String tckn, String bankaadi, String tutar, String krediTuru) {
		super();
		this.krediNumarasi = krediNumarasi;
		this.tckn = tckn;
		this.bankaadi = bankaadi;
		this.tutar = tutar;
		this.krediTuru = krediTuru;
	}

	public String getKrediNumarasi() {
		return krediNumarasi;
	}

	public void setKrediNumarasi(String krediNumarasi) {
		this.krediNumarasi = krediNumarasi;
	}

	public String getTckn() {
		return tckn;
	}

	public void setTckn(String tckn) {
		this.tckn = tckn;
	}

	public String getBankaadi() {
		return bankaadi;
	}

	public void setBankaadi(String bankaadi) {
		this.bankaadi = bankaadi;
	}

	public String getTutar() {
		return tutar;
	}

	public void setTutar(String tutar) {
		this.tutar = tutar;
	}

	public String getKrediTuru() {
		return krediTuru;
	}

	public void setKrediTuru(String krediTuru) {
		this.krediTuru = krediTuru;
	}
	
	
	


}


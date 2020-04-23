package application;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollBar;

//ECQV
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.util.encoders.Base64;

import Httputil.HttpMethod;
import ca.trustpoint.m2m.KeyAlgorithmDefinition;
import ca.trustpoint.m2m.M2mSignatureAlgorithmOids;
import ca.trustpoint.m2m.SignatureAlgorithms;
import ca.trustpoint.m2m.M2mCertPath.SupportedEncodings;
import ca.trustpoint.m2m.ecqv.EcqvProvider;
import ca.trustpoint.m2m.ecqv.EcqvProvider2;
import ca.trustpoint.m2m.ecqv.KeyReconstructionData;


public class MyController implements Initializable {
	
	private String message = null;

   //@FXML
   //private Button btn;
	@FXML
	private TextField cerECQV;
	
	@FXML
	private TextField checkECQV1;//type in cer
	@FXML
	private TextField checkECQV2;//show ver result
	
   @FXML
   private TextField cerProxy;
   
   @FXML
   private TextField cerUpdate;
   
   @FXML
   private TextField checkProxy1;
   @FXML
   private TextField checkProxy2;
   
   @FXML
   private TextField checkUpdate1;
   @FXML
   private TextField checkUpdate11;
   @FXML
   private TextField checkUpdate12;
   @FXML
   private TextField checkUpdate2;
   
   @FXML
   private ScrollBar scrollbar;

   @Override
   public void initialize(URL location, ResourceBundle resources) {

       // TODO (don't really need to do anything here).
	   
   }

   /*
   // When user click on myButton
   // this method will be called.
   public void showDateTime(ActionEvent event) {
       System.out.println("Button Clicked!");

       Date now= new Date();

       DateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
       String dateTimeString = df.format(now);
        // Show in VIEW
       cerProxy.setText(dateTimeString);

   }*/
   
   
 //OnAction function of ECQV
   //Generate ECQV
   public void genECQV(ActionEvent event) throws InvalidAlgorithmParameterException, SignatureException, IOException, IllegalArgumentException,
	UnsupportedOperationException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, CertificateEncodingException, URISyntaxException {
	   
	   System.out.println("========Request for ECQV!!========");
	   //HTTP REQUEST for server and server ask CA for ECQVcer, and then GET RESPONSE
	   //
        try {
			message = HttpMethod.GETreq(1,"","UTF-8");
			System.out.println("******MESSAGE = "+message+"    ******");
	        cerECQV.setText(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   //
	   //
	   System.out.println("========GET ECQV!!========");
   }
   
   
   
 //OnAction function of ECQV
   //verify ECQV
   public void verECQV(ActionEvent event) throws InvalidAlgorithmParameterException, SignatureException, IOException, IllegalArgumentException,
	UnsupportedOperationException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, CertificateEncodingException, URISyntaxException {
	   
	   System.out.println("========Verification for ECQV!!========");
	   //HTTP REQUEST for server and server run the verification process and send back result
	   //Get cert and send to server
	   String cer = checkECQV1.getText();
	   //
	   try {
			message = HttpMethod.GETreq(2,cer,"UTF-8");
			System.out.println("******MESSAGE = "+message+"    ******");
			checkECQV2.setText(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   //
   }
   
   
   
   //OnAction function of Method1 in paper
   //Generate proxy credential of ECQVcertificate
   public void proxyECQV(ActionEvent event) throws InvalidAlgorithmParameterException, SignatureException, IOException, IllegalArgumentException,
	UnsupportedOperationException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, CertificateEncodingException, URISyntaxException {
	   
	   System.out.println("========Generate proxy ECQV credential!!========");
	   //HTTP REQUEST for server and server generate proxy credential back
	   //
	   try {
			message = HttpMethod.GETreq(3,"","UTF-8");
			System.out.println("******MESSAGE = "+message+"    ******");
	        cerProxy.setText(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   //
	   //
	   System.out.println("========DONE!!========");
   }
   
   
 //OnAction function of Method2 in paper
   //Generate updated credential of ECQVcertificate
   public void updateECQV(ActionEvent event) throws InvalidAlgorithmParameterException, SignatureException, IOException, IllegalArgumentException,
	UnsupportedOperationException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, CertificateEncodingException, URISyntaxException {
	   
	   System.out.println("========Generate update ECQV credential!!========");
	   //HTTP REQUEST for server and server generate update credential back
	   //
	   try {
			message = HttpMethod.GETreq(5,"","UTF-8");
			System.out.println("******MESSAGE = "+message+"    ******");
	        cerUpdate.setText(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   //
	   //
	   System.out.println("========DONE!!========");
   }
   
   
 //OnAction function of Method1 in paper
   //verify proxy credential of ECQVcertificate
   public void verProxyECQV(ActionEvent event) throws InvalidAlgorithmParameterException, SignatureException, IOException, IllegalArgumentException,
	UnsupportedOperationException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, CertificateEncodingException, URISyntaxException {
	   
	   System.out.println("========Verification for proxy credential!!========");
	   //HTTP REQUEST for server and server run the verification process and send back result
	   //get and send
	   String cer = checkProxy1.getText();
	   //
	   try {
			message = HttpMethod.GETreq(4,cer,"UTF-8");
			System.out.println("******MESSAGE = "+message+"    ******");
			checkProxy2.setText(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   //;
   }
   
   
 //OnAction function of Method2 in paper
   //verify updated credential of ECQVcertificate
   public void verUpdateECQV(ActionEvent event) throws InvalidAlgorithmParameterException, SignatureException, IOException, IllegalArgumentException,
	UnsupportedOperationException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, CertificateEncodingException, URISyntaxException {
	   
	   System.out.println("========Verification for update credential!!========");
	   //HTTP REQUEST for server and server run the verification process and send back result
	   //get and send
	   String cer = checkUpdate1.getText();
	   String T = checkUpdate11.getText();
	   String z = checkUpdate12.getText();
	   System.out.println("******cert = "+cer+"    ******");
	   System.out.println("******t = "+T+"    ******");
	   System.out.println("******z = "+z+"    ******");
	   //
	   try {
			message = HttpMethod.GETreq(6, cer, T, z, "UTF-8");
			System.out.println("******MESSAGE = "+message+"    ******");
			checkUpdate2.setText(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   //
   }

}
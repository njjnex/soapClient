package by.me2.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import city.wsdl.AuthRequest;
import city.wsdl.AuthResponse;
import city.wsdl.GetCityRequest;
import city.wsdl.GetCityResponse;
import city.wsdl.ObjectFactory;

/**
 * @author Ivan Artemenko Default WebServiceGataway process request/response
 *         connections. For communicating with service authorization is
 *         required. After authorization client get unique 'sessionId' which it
 *         sent with each request to the service for validating authorities.
 * */
public class CityClient extends WebServiceGatewaySupport {

	private String SOAP_SERVER = "http://188.166.5.154:8080/micro/service/";

	/**
	 * Default user 'sign in' method
	 * 
	 * @param email - user email
	 * @param password - user password
	 * @return - session with unique generated sessionId for right credentials or 'AUTH_FAIL' for wrong 
	 * @throws NullPointerException - enable to create connection to remote
	 */
	public AuthResponse auth(String email, String password) throws NullPointerException {

		AuthRequest request = new ObjectFactory().createAuthRequest();
		request.setEMail(email);
		request.setPass(password);
		AuthResponse response = (AuthResponse) getWebServiceTemplate().marshalSendAndReceive(request,
				new SoapActionCallback(SOAP_SERVER + "Auth"));

		return response;
	}

	/**
	 * 
	 * @param session - unique user session which was provided during authorization
	 * @return - response containing ArrayList<String> cuties names
	 */
	public GetCityResponse getCity(String session) {
		GetCityRequest request = new GetCityRequest();
		request.setSession(session);

		GetCityResponse response = (GetCityResponse) getWebServiceTemplate().marshalSendAndReceive(request,
				new SoapActionCallback(SOAP_SERVER + "GetCity"));

		return response;
	}

}

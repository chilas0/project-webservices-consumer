/**
 * 
 */
package com.chilas.projectservicesconsumer.client;

import java.time.LocalDateTime;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyClientBuilder;

import com.chilas.projectservicesconsumer.dto.EmpleadoDTO;



/**
 * @author Anthony
 * Clase que permite consumir el webservice de Empleados
 */
public class EmpleadoWSClient {

	public static void main(String[] args) {
		//::::::::::::::GET::::::::::
		
		
		/*Client client = JerseyClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/project-webservices/chilas/empleadosWS").path("consultarEmpleadoPorId").path("123456");
		
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		
		if(response.getStatus() == 204) {
			System.out.println("No se encontro el empleado");
		}
		
		if(response.getStatus() == 200) {
			EmpleadoDTO empleadoDTO = response.readEntity(EmpleadoDTO.class);
			System.out.println("Nombre Empleado:" + empleadoDTO.getNombre());
			System.out.println("Fecha:" + empleadoDTO.getFechaCreacion());
		}*/
		
		Client client = ClientBuilder.newClient(new ClientConfig());
		WebTarget webTarget = client.target("http://localhost:8080/project-webservices/chilas/empleadosWS").path("guardarEmpleado");
		
		EmpleadoDTO emp = new EmpleadoDTO();
		emp.setNumeroEmpleado("1");
		emp.setNombre("Test");
		emp.setPrimerApellido("test");
		emp.setSegundoApellido("test");
		emp.setEdad(1);
		emp.setFechaCreacion(LocalDateTime.now());
		
		
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(emp,  MediaType.APPLICATION_JSON));
		
		if(response.getStatus() == 400) {
			String error = response.readEntity(String.class);
			System.out.println(error);

		}
		
		if(response.getStatus() == 200) {
			EmpleadoDTO empleadoDTO = response.readEntity(EmpleadoDTO.class);
			System.out.println(empleadoDTO.getNombre());
			System.out.println(empleadoDTO.getFechaCreacion());
		}
		
		
	}
}

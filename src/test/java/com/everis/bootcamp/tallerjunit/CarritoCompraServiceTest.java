package com.everis.bootcamp.tallerjunit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.everis.bootcamp.tallerjunit.Articulo;

public class CarritoCompraServiceTest {

	private CarritoCompraService serv1;
	private static CarritoCompraService serv;

	private BaseDeDatosService mock;

	@Before
	public void setUp() {
		serv1 = new CarritoCompraService();
		mock = Mockito.mock(BaseDeDatosService.class);
		serv1.setBbddService(mock);
		System.out.println("Inicializacion de servicio ");
	}
	
	@Test(expected=NullPointerException.class)
	public void aplicarDescuentoTest(){
		Mockito.when(mock.findArticuloById(1)).thenReturn(new Articulo("MI MOCK", 10.00));
		Double res = serv1.aplicarDescuento(1, 12.0);
		System.out.println("El precio despues del descuento es: " + res);
		Mockito.when(mock.findArticuloById(0)).thenThrow(NullPointerException.class);
		assertEquals(231, serv1.aplicarDescuento(0, 12),0);
	}
	

	@BeforeClass
	public static void settUp() {
		serv = new CarritoCompraService();
		System.out.println("Inicializacion de servicio ");
	}

	@Test
	public void AAaddArticuloTest() {
		System.out.println("Probando addArticulo \n");
		assertTrue(serv.getArticulos().isEmpty());
		serv.addArticulo(new Articulo("Articulo 1", 10d));
		serv.addArticulo(new Articulo("Articulo 1", 20d));
		serv.addArticulo(new Articulo("Articulo 1", 30d));
		assertFalse(serv.getArticulos().isEmpty());
	}

	@Test
	public void ABtotalPriceTest() {
		System.out.println("Probando totalPrice \n");
		assertFalse(serv.getArticulos().isEmpty());
		assertTrue(serv.totalPrice() == 60d);

	}

	@Test
	public void AClimpiarCestaTest() {
		System.out.println("Probando limparCesta \n");
		assertFalse(serv.getArticulos().isEmpty());
		serv.limpiarCesta();
		assertTrue(serv.getArticulos().isEmpty());
	}

	@Test
	public void limpiarCestaTest() {
		System.out.println("Probando limparCesta \n");
		assertTrue(serv1.getArticulos().isEmpty());
		serv1.addArticulo(new Articulo("", 2d));
		assertFalse(serv1.getArticulos().isEmpty());
		serv1.limpiarCesta();
		assertTrue(serv1.getArticulos().isEmpty());
	}

	@Test
	public void addArticuloTest() {
		System.out.println("Probando addArticulo \n");
		assertTrue(serv.getArticulos().isEmpty());
		serv1.addArticulo(new Articulo("Articulo 1", 10d));
		assertFalse(serv1.getArticulos().isEmpty());
	}

	@Test
	public void getNumArticuloTest() {
		System.out.println("Probando getNumArticulo \n");
		assertTrue(serv1.getNumArticulo() == 0);
		serv1.addArticulo(new Articulo("Art 1", 1d));
		assertTrue(serv1.getArticulos().size() > 0);
	}

	@Test
	public void totalPriceTest() {
		System.out.println("Probando totalPrice \n");
		assertTrue(serv1.getArticulos().isEmpty());
		serv1.addArticulo(new Articulo("Art 1", 1d));
		assertTrue(serv1.totalPrice() == 1d);
		serv1.addArticulo(new Articulo("Art 2", 2d));
		assertTrue(serv1.totalPrice() == 3d);

	}

	@Test
	public void calculadorDescuentoTest() {
		System.out.println("Probando calculadorDescuento \n");
		Double a = CarritoCompraService.calculadorDescuento(2, 10);
		Double b = 2.0 - (2.0 * (10.0 / 100.0));
		assertEquals(a, b, 0);
	}

}

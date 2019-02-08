package com.ipartek.formacion.taller.modelo.pojo;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;

import com.ipartek.formacion.taller.modelo.pojo.validactions.VehiculoCompleteCheck;
import com.ipartek.formacion.taller.modelo.pojo.validactions.VehiculosPostCheck;

public class VehiculoTest {

	@Test
	public void test() {

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Vehiculo vCompleteCheck = new Vehiculo();

		Set<ConstraintViolation<Vehiculo>> vComplete = validator.validate(vCompleteCheck, VehiculoCompleteCheck.class);

		assertEquals("Deberia haber 5 violaciones", 5, vComplete.size());

		vCompleteCheck.setMatricula("12");
		vComplete = validator.validate(vCompleteCheck, VehiculoCompleteCheck.class);
		assertEquals(4, vComplete.size());

		vCompleteCheck.setMatricula("12345678");
		vComplete = validator.validate(vCompleteCheck, VehiculoCompleteCheck.class);
		assertEquals("quitamos violacion Size(min=8, max =10)", 3, vComplete.size());

	}

}

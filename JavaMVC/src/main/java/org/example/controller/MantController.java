package org.example.controller;

import org.example.models.Mantenimiento;
import org.example.models.Car;

import org.example.services.MantService;

import java.util.Date;
import java.util.List;

public class MantController {

    private final MantService mantService;

    public MantController(MantService mantService) {
        this.mantService = mantService;
    }

    public Mantenimiento createMantenimiento(Long id, Date fecha,String descripcion, String tipo, Car car) {
        return mantService.createMantenimiento(id , fecha,descripcion,tipo,car);
    }

    public List<Mantenimiento> getMantenimientoByCar(Car car) {
        return mantService.getMantenimientosByCar(car);
    }


    public Mantenimiento getMantenimientoById(Long id) {
        return mantService.getMantenimientoById(id);
    }


    public Mantenimiento updateMantenimiento(Mantenimiento mant) {
        return mantService.updateMantenimiento(mant.getId(), mant.getFecha(), mant.getDescripcion(), mant.getTipo());
    }

    public boolean deleteMantenimiento(Long id) {
        return mantService.deleteCar(id);
    }
}

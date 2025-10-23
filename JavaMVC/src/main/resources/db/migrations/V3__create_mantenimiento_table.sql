CREATE TABLE mantenimiento (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE,
    car_id BIGINT NOT NULL,
    descripcion VARCHAR(50),
    tipo VARCHAR(15),
    CONSTRAINT fk_mantenimiento_car FOREIGN KEY (car_id) REFERENCES cars(id) ON DELETE CASCADE
);
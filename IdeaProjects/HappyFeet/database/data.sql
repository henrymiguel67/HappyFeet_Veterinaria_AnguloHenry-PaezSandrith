USE HappyFeet;

-- =========== DATOS PARA TABLAS DE CONSULTA ===========

-- Especies
INSERT INTO especies (nombre) VALUES 
('Perro'),
('Gato'),
('Ave'),
('Conejo'),
('Hámster');

-- Razas
INSERT INTO razas (especie_id, nombre) VALUES 
(1, 'Labrador Retriever'),
(1, 'Pastor Alemán'),
(1, 'Bulldog Francés'),
(1, 'Chihuahua'),
(2, 'Siamés'),
(2, 'Persa'),
(2, 'Maine Coon'),
(2, 'Bengalí'),
(3, 'Perico Australiano'),
(3, 'Canario'),
(4, 'Conejo Enano'),
(4, 'Conejo Holandés'),
(5, 'Hámster Sirio'),
(5, 'Hámster Enano');

-- Tipos de Producto
INSERT INTO producto_tipos (nombre) VALUES 
('Medicamento'),
('Vacuna'),
('Insumo Médico'),
('Alimento'),
('Juguete'),
('Accesorio');

-- Tipos de Evento
INSERT INTO evento_tipos (nombre) VALUES 
('Consulta General'),
('Vacunación'),
('Cirugía'),
('Desparasitación'),
('Limpieza Dental'),
('Urgencia'),
('Control Post-operatorio');

-- Estados de Cita
INSERT INTO cita_estados (nombre) VALUES 
('Programada'),
('En Proceso'),
('Finalizada'),
('Cancelada'),
('No Asistió');

-- =========== DATOS PARA TABLAS OPERACIONALES ===========

-- Dueños
INSERT INTO duenos (nombre_completo, documento_identidad, direccion, telefono, email) VALUES 
('María González Pérez', '12345678A', 'Calle Primavera 123, Ciudad', '600111222', 'maria.gonzalez@email.com'),
('Carlos Rodríguez López', '87654321B', 'Avenida Central 456, Ciudad', '600333444', 'carlos.rodriguez@email.com'),
('Ana Martínez Sánchez', '11223344C', 'Plaza Mayor 789, Ciudad', '600555666', 'ana.martinez@email.com'),
('David Fernández García', '44332211D', 'Calle Luna 321, Ciudad', '600777888', 'david.fernandez@email.com'),
('Laura Díaz Romero', '55667788E', 'Avenida Sol 654, Ciudad', '600999000', 'laura.diaz@email.com');

-- Mascotas
INSERT INTO mascotas (dueno_id, nombre, raza_id, fecha_nacimiento, sexo, url_foto) VALUES 
(1, 'Max', 1, '2020-05-15', 'Macho', 'https://ejemplo.com/fotos/max.jpg'),
(1, 'Luna', 5, '2021-02-20', 'Hembra', 'https://ejemplo.com/fotos/luna.jpg'),
(2, 'Rocky', 2, '2019-08-10', 'Macho', 'https://ejemplo.com/fotos/rocky.jpg'),
(3, 'Mimi', 6, '2022-01-30', 'Hembra', 'https://ejemplo.com/fotos/mimi.jpg'),
(4, 'Toby', 3, '2020-11-25', 'Macho', 'https://ejemplo.com/fotos/toby.jpg'),
(5, 'Nala', 7, '2021-07-12', 'Hembra', 'https://ejemplo.com/fotos/nala.jpg');

-- Inventario
INSERT INTO inventario (nombre_producto, producto_tipo_id, descripcion, fabricante, lote, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta) VALUES 
('Vacuna Triple Felina', 2, 'Vacuna para gatos contra panleucopenia, calicivirus y rinotraqueitis', 'LabVet', 'VT202401', 50, 10, '2025-06-30', 45.00),
('Antipulgas Spot-on Perro', 1, 'Tratamiento antipulgas y garrapatas para perros', 'PetCare', 'AP202402', 30, 5, '2026-12-31', 25.50),
('Alimento Premium Perro Adulto', 4, 'Alimento balanceado para perros adultos razas medianas', 'NutriPet', 'AL202403', 100, 20, '2025-09-15', 80.00),
('Jeringas Estériles 10ml', 3, 'Jeringas desechables para uso veterinario', 'MedVet', 'JE202404', 200, 50, '2027-03-20', 1.20),
('Analgésico Inyectable', 1, 'Analgésico para dolor moderado a severo', 'PharmaVet', 'AI202405', 40, 8, '2025-11-30', 15.75),
('Vacuna Antirrábica', 2, 'Vacuna contra la rabia para perros y gatos', 'InmunoVet', 'VR202406', 35, 7, '2025-08-15', 38.90);

-- Historial Médico
INSERT INTO historial_medico (mascota_id, fecha_evento, evento_tipo_id, descripcion, diagnostico, tratamiento_recomendado) VALUES 
(1, '2024-01-15', 2, 'Vacunación anual', 'Saludable', 'Aplicada vacuna triple canina. Próxima dosis en 1 año'),
(2, '2024-01-20', 1, 'Consulta por estornudos frecuentes', 'Resfriado común felino', 'Reposo y medicamento para síntomas. Seguimiento en 1 semana'),
(3, '2024-02-01', 4, 'Desparasitación interna y externa', 'Sin parásitos detectados', 'Aplicado antiparasitario. Próxima dosis en 3 meses'),
(4, '2024-02-10', 1, 'Control de peso y salud general', 'Peso ideal, salud óptima', 'Mantener dieta actual y ejercicio regular'),
(1, '2024-02-15', 1, 'Revisión por cojera en pata trasera', 'Esguince leve', 'Reposo por 1 semana y antiinflamatorio');

-- Citas
INSERT INTO citas (mascota_id, fecha_hora, motivo, estado_id) VALUES 
(2, '2024-03-01 10:00:00', 'Control post-resfriado', 1),
(3, '2024-03-02 11:30:00', 'Vacunación antirrábica', 1),
(5, '2024-03-03 09:15:00', 'Limpieza dental programada', 1),
(1, '2024-03-04 16:00:00', 'Revisión esguince', 1);

-- Facturas
INSERT INTO facturas (dueno_id, fecha_emision, total) VALUES 
(1, '2024-01-15 11:30:00', 45.00),
(2, '2024-01-20 12:15:00', 65.50),
(3, '2024-02-01 10:45:00', 25.50),
(1, '2024-02-15 17:20:00', 85.75);

-- Items de Factura
INSERT INTO items_factura (factura_id, producto_id, servicio_descripcion, cantidad, precio_unitario, subtotal) VALUES 
(1, 1, NULL, 1, 45.00, 45.00),
(2, NULL, 'Consulta de urgencia', 1, 40.00, 40.00),
(2, 2, NULL, 1, 25.50, 25.50),
(3, 2, NULL, 1, 25.50, 25.50),
(4, NULL, 'Consulta general', 1, 35.00, 35.00),
(4, 5, NULL, 1, 15.75, 15.75),
(4, 4, NULL, 3, 1.20, 3.60),
(4, NULL, 'Vendaje y curación', 1, 31.40, 31.40);

-- Mensaje de confirmación
SELECT '✅ Datos de prueba insertados correctamente' AS Estado;
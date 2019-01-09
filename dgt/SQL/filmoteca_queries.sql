-- De un actor que películas ha hecho

SELECT
	a.id 'id_actor',
	a.nombre 'actor', 
    p.id 'id_pelicula',
    p.nombre 'pelicula'
FROM
	actor a,
    reparto r,
    pelicula p
WHERE 
	a.id = r.id_actor AND
    p.id = r.id_pelicula AND
    a.id = 3;

-- detalle completo de una película

SELECT
	p.id 'id_pelicula',
	p.nombre 'pelicula',
    duracion,
    estreno 'fecha_de_estreno',
    p.id_categoria 'id_categoria',
    c.nombre 'categoria', 
    p.id_director 'id_director',
    d.nombre 'director',
    a.id 'id_actor',
    a.nombre 'actor',
    salario
FROM
	actor a,
    reparto r,
    pelicula p,
    director d,
    categoria c
WHERE 
	a.id = r.id_actor AND
    p.id = r.id_pelicula AND
    p.id_categoria = c.id AND
    p.id_director = d.id
ORDER BY
	p.nombre;
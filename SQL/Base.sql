--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

-- Started on 2024-04-01 15:09:03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 224 (class 1259 OID 35407)
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    idcliente character(13) NOT NULL,
    direccion character varying(20)
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 35559)
-- Name: factura; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.factura (
    idfactura character(6) NOT NULL,
    fechaventa date
);


ALTER TABLE public.factura OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 35624)
-- Name: facturacliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.facturacliente (
    idfacturac character(6) NOT NULL,
    idclientef character(13)
);


ALTER TABLE public.facturacliente OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 35594)
-- Name: facturaproducto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.facturaproducto (
    idfacturap character(6) NOT NULL,
    idproductof character(13) NOT NULL,
    cantidad integer
);


ALTER TABLE public.facturaproducto OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 35277)
-- Name: galletas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.galletas (
    idgalletas character varying(6) NOT NULL,
    tipo character varying(30),
    extra character varying(30),
    stock integer,
    fechavencimiento date
);


ALTER TABLE public.galletas OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 43699)
-- Name: inventario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inventario (
    idingrediente character(6) NOT NULL,
    precioingrediente integer,
    nombreingrediente character varying(50),
    cantdisponible integer,
    fechavencimiento date
);


ALTER TABLE public.inventario OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 35287)
-- Name: pan; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pan (
    idpan character varying(6) NOT NULL,
    tipo character varying(30),
    sabor character varying(30),
    stock integer,
    fechavencimiento date
);


ALTER TABLE public.pan OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 35396)
-- Name: persona; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.persona (
    idpersona character(13) NOT NULL,
    nombre character varying(50),
    correo character varying(25),
    contacto character(9)
);


ALTER TABLE public.persona OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 35427)
-- Name: personal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.personal (
    idpersonal character(13) NOT NULL,
    salario double precision,
    puesto character varying(50)
);


ALTER TABLE public.personal OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 35349)
-- Name: pnormal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pnormal (
    idpastelnormal character(6) NOT NULL,
    tipo character varying(20),
    sabor character varying(20),
    relleno character varying(20),
    betun character varying(20),
    stock integer,
    fechavencimiento date
);


ALTER TABLE public.pnormal OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 35301)
-- Name: ppersonalizado; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ppersonalizado (
    idppersonalizado character(6) NOT NULL,
    detalles character varying(50),
    betun character varying(20),
    fechaentrega date
);


ALTER TABLE public.ppersonalizado OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 35262)
-- Name: producto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.producto (
    idproducto character(6) NOT NULL,
    precioproducto double precision,
    fechaelaboracion date
);


ALTER TABLE public.producto OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 35344)
-- Name: proveedores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.proveedores (
    idproveedor character(6) NOT NULL,
    nombre character varying(40),
    contacto character(8),
    detalles character varying(30)
);


ALTER TABLE public.proveedores OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 35311)
-- Name: torta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.torta (
    idtorta character(6) NOT NULL,
    tipotorta character varying(50),
    precio double precision
);


ALTER TABLE public.torta OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 35381)
-- Name: tortapersonalizada; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tortapersonalizada (
    idtorta character(6) NOT NULL,
    idppersonalizado character(6) NOT NULL,
    cantidad integer,
    sabor character varying(20),
    relleno character varying(20)
);


ALTER TABLE public.tortapersonalizada OWNER TO postgres;

--
-- TOC entry 4883 (class 0 OID 35407)
-- Dependencies: 224
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cliente (idcliente, direccion) FROM stdin;
0316200100112	Comayagua
0316200100115	Comayagua
0316200200017	Ajuterique
\.


--
-- TOC entry 4885 (class 0 OID 35559)
-- Dependencies: 226
-- Data for Name: factura; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.factura (idfactura, fechaventa) FROM stdin;
FA0001	2024-03-11
FA0002	2024-03-12
FA0003	2024-03-13
FA0004	2024-03-07
FA0005	2024-03-09
FA0006	2024-03-20
FA0007	2024-03-20
\.


--
-- TOC entry 4887 (class 0 OID 35624)
-- Dependencies: 228
-- Data for Name: facturacliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.facturacliente (idfacturac, idclientef) FROM stdin;
FA0001	0316200100112
\.


--
-- TOC entry 4886 (class 0 OID 35594)
-- Dependencies: 227
-- Data for Name: facturaproducto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.facturaproducto (idfacturap, idproductof, cantidad) FROM stdin;
FA0001	PN0001       	1
FA0002	GA0001       	2
FA0002	GA0002       	1
FA0002	PA0001       	2
FA0003	GA0001       	3
FA0003	PN0002       	2
FA0003	PN0001       	1
FA0004	PA0001       	10
FA0004	PA0002       	12
FA0005	PP0001       	1
FA0006	PP0002       	1
FA0007	PP0003       	1
\.


--
-- TOC entry 4875 (class 0 OID 35277)
-- Dependencies: 216
-- Data for Name: galletas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.galletas (idgalletas, tipo, extra, stock, fechavencimiento) FROM stdin;
GA0001	Saladas	Soda	50	2024-07-03
GA0002	Rellenas	Chispas	100	2024-07-03
GA0003	Saladas	Queso	10	2024-02-01
\.


--
-- TOC entry 4888 (class 0 OID 43699)
-- Dependencies: 229
-- Data for Name: inventario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inventario (idingrediente, precioingrediente, nombreingrediente, cantdisponible, fechavencimiento) FROM stdin;
IG0001	20	Azucar	2	2024-03-30
\.


--
-- TOC entry 4876 (class 0 OID 35287)
-- Dependencies: 217
-- Data for Name: pan; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pan (idpan, tipo, sabor, stock, fechavencimiento) FROM stdin;
PA0001	Pan Blanco	\N	10	2024-09-08
PA0002	Semita	\N	12	2024-09-08
PA0003	Pan de Banano	\N	10	2024-09-08
\.


--
-- TOC entry 4882 (class 0 OID 35396)
-- Dependencies: 223
-- Data for Name: persona; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.persona (idpersona, nombre, correo, contacto) FROM stdin;
0316200100112	Raul Blanco	raul@gmail.com	11223366 
0316200100115	Marta Hidalgo	marta@gmail.com	44771122 
0308011254684	Hugo Ortiz	hugo@gmail.com	88774455 
0301164822554	Franco Escamilla	franco@gmail.com	44778899 
0316200200017	Maria Gimenez	maria@gmail.com	99886655 
0316200200012	Francisco Morazan	francisco@gmail.com	96321458 
0301200220021	Leo Messi	leo@gmail.com	7788554  
\.


--
-- TOC entry 4884 (class 0 OID 35427)
-- Dependencies: 225
-- Data for Name: personal; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.personal (idpersonal, salario, puesto) FROM stdin;
0308011254684	60000	Chef
0301164822554	15000	Aseador
0316200200012	12000	Administrador
\.


--
-- TOC entry 4880 (class 0 OID 35349)
-- Dependencies: 221
-- Data for Name: pnormal; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pnormal (idpastelnormal, tipo, sabor, relleno, betun, stock, fechavencimiento) FROM stdin;
PN0002	Helado	Vainilla	Vainilla	Vainilla	3	2024-04-02
\.


--
-- TOC entry 4877 (class 0 OID 35301)
-- Dependencies: 218
-- Data for Name: ppersonalizado; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ppersonalizado (idppersonalizado, detalles, betun, fechaentrega) FROM stdin;
PP0001	Diseño  de Boda	Chocolate	2024-06-01
PP0002	Diseño de Carro	Coco	2024-06-01
PP0003	Diseño de Tractor color verde	Chocolate	2024-06-01
\.


--
-- TOC entry 4874 (class 0 OID 35262)
-- Dependencies: 215
-- Data for Name: producto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.producto (idproducto, precioproducto, fechaelaboracion) FROM stdin;
GA0001	30	2024-02-18
GA0002	42	2024-02-18
PA0001	60	2024-02-18
PN0001	1200	2024-02-18
PA0002	35	2024-02-18
PA0003	50	2024-02-18
PN0002	2100	2024-02-18
PP0001	130	2024-02-18
PP0002	650	2024-02-18
PP0003	1430	2024-02-18
GA0003	20	2024-01-01
\.


--
-- TOC entry 4879 (class 0 OID 35344)
-- Dependencies: 220
-- Data for Name: proveedores; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.proveedores (idproveedor, nombre, contacto, detalles) FROM stdin;
PR0001	Sula S.A	99887766	Proveedor de Lacteos
\.


--
-- TOC entry 4878 (class 0 OID 35311)
-- Dependencies: 219
-- Data for Name: torta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.torta (idtorta, tipotorta, precio) FROM stdin;
TT0001	3-5 personas	100
TT0002	5-8 personas	200
TT0003	8-10 personas	300
TT0004	10-15 personas	450
TT0005	15-20 personas	550
TT0006	20-25 personas	600
\.


--
-- TOC entry 4881 (class 0 OID 35381)
-- Dependencies: 222
-- Data for Name: tortapersonalizada; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tortapersonalizada (idtorta, idppersonalizado, cantidad, sabor, relleno) FROM stdin;
TT0001	PP0001	1	Vainilla	Chocolate 
TT0001	PP0002	1	Chocolate	Chocolate
TT0002	PP0002	2	Fresa	Vainilla
TT0005	PP0003	2	Nutella	Vainilla
\.


--
-- TOC entry 4708 (class 2606 OID 35411)
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (idcliente);


--
-- TOC entry 4712 (class 2606 OID 35563)
-- Name: factura factura_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura
    ADD CONSTRAINT factura_pkey PRIMARY KEY (idfactura);


--
-- TOC entry 4716 (class 2606 OID 35628)
-- Name: facturacliente facturacliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.facturacliente
    ADD CONSTRAINT facturacliente_pkey PRIMARY KEY (idfacturac);


--
-- TOC entry 4714 (class 2606 OID 35598)
-- Name: facturaproducto facturaproducto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.facturaproducto
    ADD CONSTRAINT facturaproducto_pkey PRIMARY KEY (idfacturap, idproductof);


--
-- TOC entry 4692 (class 2606 OID 35281)
-- Name: galletas galletas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.galletas
    ADD CONSTRAINT galletas_pkey PRIMARY KEY (idgalletas);


--
-- TOC entry 4718 (class 2606 OID 43703)
-- Name: inventario inventario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inventario
    ADD CONSTRAINT inventario_pkey PRIMARY KEY (idingrediente);


--
-- TOC entry 4694 (class 2606 OID 35291)
-- Name: pan pan_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pan
    ADD CONSTRAINT pan_pkey PRIMARY KEY (idpan);


--
-- TOC entry 4706 (class 2606 OID 35400)
-- Name: persona persona_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (idpersona);


--
-- TOC entry 4710 (class 2606 OID 35431)
-- Name: personal personal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.personal
    ADD CONSTRAINT personal_pkey PRIMARY KEY (idpersonal);


--
-- TOC entry 4702 (class 2606 OID 35353)
-- Name: pnormal pnormal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pnormal
    ADD CONSTRAINT pnormal_pkey PRIMARY KEY (idpastelnormal);


--
-- TOC entry 4696 (class 2606 OID 35305)
-- Name: ppersonalizado ppersonalizado_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ppersonalizado
    ADD CONSTRAINT ppersonalizado_pkey PRIMARY KEY (idppersonalizado);


--
-- TOC entry 4690 (class 2606 OID 35266)
-- Name: producto producto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (idproducto);


--
-- TOC entry 4700 (class 2606 OID 35348)
-- Name: proveedores proveedores_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.proveedores
    ADD CONSTRAINT proveedores_pkey PRIMARY KEY (idproveedor);


--
-- TOC entry 4698 (class 2606 OID 35380)
-- Name: torta torta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.torta
    ADD CONSTRAINT torta_pkey PRIMARY KEY (idtorta);


--
-- TOC entry 4704 (class 2606 OID 35385)
-- Name: tortapersonalizada tortapersonalizada_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tortapersonalizada
    ADD CONSTRAINT tortapersonalizada_pkey PRIMARY KEY (idtorta, idppersonalizado);


--
-- TOC entry 4725 (class 2606 OID 35412)
-- Name: cliente cliente_idcliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_idcliente_fkey FOREIGN KEY (idcliente) REFERENCES public.persona(idpersona);


--
-- TOC entry 4729 (class 2606 OID 35634)
-- Name: facturacliente facturacliente_idclientef_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.facturacliente
    ADD CONSTRAINT facturacliente_idclientef_fkey FOREIGN KEY (idclientef) REFERENCES public.cliente(idcliente);


--
-- TOC entry 4730 (class 2606 OID 35629)
-- Name: facturacliente facturacliente_idfacturac_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.facturacliente
    ADD CONSTRAINT facturacliente_idfacturac_fkey FOREIGN KEY (idfacturac) REFERENCES public.factura(idfactura);


--
-- TOC entry 4727 (class 2606 OID 35599)
-- Name: facturaproducto facturaproducto_idfacturap_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.facturaproducto
    ADD CONSTRAINT facturaproducto_idfacturap_fkey FOREIGN KEY (idfacturap) REFERENCES public.factura(idfactura);


--
-- TOC entry 4728 (class 2606 OID 35604)
-- Name: facturaproducto facturaproducto_idproductof_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.facturaproducto
    ADD CONSTRAINT facturaproducto_idproductof_fkey FOREIGN KEY (idproductof) REFERENCES public.producto(idproducto);


--
-- TOC entry 4719 (class 2606 OID 35282)
-- Name: galletas galletas_idgalletas_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.galletas
    ADD CONSTRAINT galletas_idgalletas_fkey FOREIGN KEY (idgalletas) REFERENCES public.producto(idproducto);


--
-- TOC entry 4720 (class 2606 OID 35292)
-- Name: pan pan_idpan_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pan
    ADD CONSTRAINT pan_idpan_fkey FOREIGN KEY (idpan) REFERENCES public.producto(idproducto);


--
-- TOC entry 4726 (class 2606 OID 35432)
-- Name: personal personal_idpersonal_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.personal
    ADD CONSTRAINT personal_idpersonal_fkey FOREIGN KEY (idpersonal) REFERENCES public.persona(idpersona);


--
-- TOC entry 4722 (class 2606 OID 35354)
-- Name: pnormal pnormal_idpastelnormal_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pnormal
    ADD CONSTRAINT pnormal_idpastelnormal_fkey FOREIGN KEY (idpastelnormal) REFERENCES public.producto(idproducto);


--
-- TOC entry 4721 (class 2606 OID 35306)
-- Name: ppersonalizado ppersonalizado_idppersonalizado_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ppersonalizado
    ADD CONSTRAINT ppersonalizado_idppersonalizado_fkey FOREIGN KEY (idppersonalizado) REFERENCES public.producto(idproducto);


--
-- TOC entry 4723 (class 2606 OID 35386)
-- Name: tortapersonalizada tortapersonalizada_idppersonalizado_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tortapersonalizada
    ADD CONSTRAINT tortapersonalizada_idppersonalizado_fkey FOREIGN KEY (idppersonalizado) REFERENCES public.producto(idproducto);


--
-- TOC entry 4724 (class 2606 OID 35391)
-- Name: tortapersonalizada tortapersonalizada_idtorta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tortapersonalizada
    ADD CONSTRAINT tortapersonalizada_idtorta_fkey FOREIGN KEY (idtorta) REFERENCES public.torta(idtorta);


-- Completed on 2024-04-01 15:09:03

--
-- PostgreSQL database dump complete
--


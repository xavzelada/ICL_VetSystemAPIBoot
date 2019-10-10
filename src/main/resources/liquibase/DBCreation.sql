--

CREATE SCHEMA vetsystem;


ALTER SCHEMA vetsystem OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16826)
-- Name: branch; Type: TABLE; Schema: vetsystem; Owner: postgres
--

CREATE TABLE vetsystem.branch (
    branchid integer NOT NULL,
    companyid integer,
    name character varying(128) NOT NULL,
    address character varying(1024),
    location character varying(1024),
    phonenumber1 character varying(15),
    phonenumber2 character varying(15),
    isactive character varying(1) DEFAULT 'A'::character varying NOT NULL,
    creationdate date DEFAULT now() NOT NULL,
    createdby character varying(25) DEFAULT USER NOT NULL,
    lastupdate date,
    updateby character varying(25),
    company_id1 integer
);


ALTER TABLE vetsystem.branch OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16824)
-- Name: branch_branchid_seq; Type: SEQUENCE; Schema: vetsystem; Owner: postgres
--

CREATE SEQUENCE vetsystem.branch_branchid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE vetsystem.branch_branchid_seq OWNER TO postgres;

--
-- TOC entry 2967 (class 0 OID 0)
-- Dependencies: 206
-- Name: branch_branchid_seq; Type: SEQUENCE OWNED BY; Schema: vetsystem; Owner: postgres
--

ALTER SEQUENCE vetsystem.branch_branchid_seq OWNED BY vetsystem.branch.branchid;


--
-- TOC entry 217 (class 1259 OID 17041)
-- Name: checkup; Type: TABLE; Schema: vetsystem; Owner: postgres
--

CREATE TABLE vetsystem.checkup (
    checkupid integer NOT NULL,
    vetid integer,
    checkupdate date,
    isactive character varying(1) DEFAULT 'A'::character varying NOT NULL,
    creationdate date DEFAULT now() NOT NULL,
    createdby character varying(25) DEFAULT USER NOT NULL,
    lastupdate date,
    updateby character varying(25)
);


ALTER TABLE vetsystem.checkup OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 17039)
-- Name: checkup_checkupid_seq; Type: SEQUENCE; Schema: vetsystem; Owner: postgres
--

CREATE SEQUENCE vetsystem.checkup_checkupid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE vetsystem.checkup_checkupid_seq OWNER TO postgres;

--
-- TOC entry 2969 (class 0 OID 0)
-- Dependencies: 216
-- Name: checkup_checkupid_seq; Type: SEQUENCE OWNED BY; Schema: vetsystem; Owner: postgres
--

ALTER SEQUENCE vetsystem.checkup_checkupid_seq OWNED BY vetsystem.checkup.checkupid;


--
-- TOC entry 219 (class 1259 OID 17057)
-- Name: checkupreport; Type: TABLE; Schema: vetsystem; Owner: postgres
--

CREATE TABLE vetsystem.checkupreport (
    checkupreportid integer NOT NULL,
    checkupid integer,
    petid integer,
    reportnotes bytea,
    reportattachment bytea,
    isactive character varying(1) DEFAULT 'A'::character varying NOT NULL,
    creationdate date DEFAULT now() NOT NULL,
    createdby character varying(25) DEFAULT USER NOT NULL,
    lastupdate date,
    updateby character varying(25),
    pet bytea,
    pet_id integer,
    pet_id1 integer
);


ALTER TABLE vetsystem.checkupreport OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 17055)
-- Name: checkupreport_checkupreportid_seq; Type: SEQUENCE; Schema: vetsystem; Owner: postgres
--

CREATE SEQUENCE vetsystem.checkupreport_checkupreportid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE vetsystem.checkupreport_checkupreportid_seq OWNER TO postgres;

--
-- TOC entry 2971 (class 0 OID 0)
-- Dependencies: 218
-- Name: checkupreport_checkupreportid_seq; Type: SEQUENCE OWNED BY; Schema: vetsystem; Owner: postgres
--

ALTER SEQUENCE vetsystem.checkupreport_checkupreportid_seq OWNED BY vetsystem.checkupreport.checkupreportid;


--
-- TOC entry 205 (class 1259 OID 16783)
-- Name: company; Type: TABLE; Schema: vetsystem; Owner: postgres
--

CREATE TABLE vetsystem.company (
    companyid integer NOT NULL,
    name character varying(128) NOT NULL,
    address character varying(1024),
    logo bytea,
    isactive character varying(1) DEFAULT 'A'::character varying NOT NULL,
    creationdate date DEFAULT now() NOT NULL,
    createdby character varying(25) DEFAULT USER NOT NULL,
    lastupdate date,
    updateby character varying(25)
);


ALTER TABLE vetsystem.company OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16781)
-- Name: company_comanyid_seq; Type: SEQUENCE; Schema: vetsystem; Owner: postgres
--

CREATE SEQUENCE vetsystem.company_comanyid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE vetsystem.company_comanyid_seq OWNER TO postgres;

--
-- TOC entry 2973 (class 0 OID 0)
-- Dependencies: 204
-- Name: company_comanyid_seq; Type: SEQUENCE OWNED BY; Schema: vetsystem; Owner: postgres
--

ALTER SEQUENCE vetsystem.company_comanyid_seq OWNED BY vetsystem.company.companyid;


--
-- TOC entry 222 (class 1259 OID 17246)
-- Name: databasechangelog; Type: TABLE; Schema: vetsystem; Owner: postgres
--

CREATE TABLE vetsystem.databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);


ALTER TABLE vetsystem.databasechangelog OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 17241)
-- Name: databasechangeloglock; Type: TABLE; Schema: vetsystem; Owner: postgres
--

CREATE TABLE vetsystem.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE vetsystem.databasechangeloglock OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16973)
-- Name: employee; Type: TABLE; Schema: vetsystem; Owner: postgres
--

CREATE TABLE vetsystem.employee (
    employeeid integer NOT NULL,
    branchid integer,
    name character varying(128) NOT NULL,
    surname character varying(128) NOT NULL,
    photo bytea,
    birthdate date,
    address character varying(1024),
    phonenumber1 character varying(15),
    phonenumber2 character varying(15),
    personalemail character varying(256),
    corporativeemail character varying(256),
    role character varying(2) NOT NULL,
    isactive character varying(1) DEFAULT 'A'::character varying NOT NULL,
    creationdate date DEFAULT now() NOT NULL,
    createdby character varying(25) DEFAULT USER NOT NULL,
    lastupdate date,
    updateby character varying(25),
    branchid1 integer
);


ALTER TABLE vetsystem.employee OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16971)
-- Name: employee_employeeid_seq; Type: SEQUENCE; Schema: vetsystem; Owner: postgres
--

CREATE SEQUENCE vetsystem.employee_employeeid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE vetsystem.employee_employeeid_seq OWNER TO postgres;

--
-- TOC entry 2975 (class 0 OID 0)
-- Dependencies: 208
-- Name: employee_employeeid_seq; Type: SEQUENCE OWNED BY; Schema: vetsystem; Owner: postgres
--

ALTER SEQUENCE vetsystem.employee_employeeid_seq OWNED BY vetsystem.employee.employeeid;


--
-- TOC entry 220 (class 1259 OID 17180)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: vetsystem; Owner: postgres
--

CREATE SEQUENCE vetsystem.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE vetsystem.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 17008)
-- Name: owner; Type: TABLE; Schema: vetsystem; Owner: postgres
--

CREATE TABLE vetsystem.owner (
    ownerid integer NOT NULL,
    name character varying(128) NOT NULL,
    surname character varying(128) NOT NULL,
    address1 character varying(1024),
    address2 character varying(1024),
    phonenumber1 character varying(15),
    phonenumber2 character varying(15),
    email character varying(256),
    photo bytea,
    birthdate date,
    isactive character varying(1) DEFAULT 'A'::character varying NOT NULL,
    creationdate date DEFAULT now() NOT NULL,
    createdby character varying(25) DEFAULT USER NOT NULL,
    lastupdate date,
    updateby character varying(25)
);


ALTER TABLE vetsystem.owner OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 17006)
-- Name: owner_ownerid_seq; Type: SEQUENCE; Schema: vetsystem; Owner: postgres
--

CREATE SEQUENCE vetsystem.owner_ownerid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE vetsystem.owner_ownerid_seq OWNER TO postgres;

--
-- TOC entry 2977 (class 0 OID 0)
-- Dependencies: 212
-- Name: owner_ownerid_seq; Type: SEQUENCE OWNED BY; Schema: vetsystem; Owner: postgres
--

ALTER SEQUENCE vetsystem.owner_ownerid_seq OWNED BY vetsystem.owner.ownerid;


--
-- TOC entry 215 (class 1259 OID 17022)
-- Name: pet; Type: TABLE; Schema: vetsystem; Owner: postgres
--

CREATE TABLE vetsystem.pet (
    petid integer NOT NULL,
    ownerid integer,
    name character varying(128) NOT NULL,
    animaltype character varying(128) NOT NULL,
    breedname character varying(128) NOT NULL,
    birthplace character varying(128) NOT NULL,
    birthdate date NOT NULL,
    photo bytea,
    isactive character varying(1) DEFAULT 'A'::character varying NOT NULL,
    creationdate date DEFAULT now() NOT NULL,
    createdby character varying(25) DEFAULT USER NOT NULL,
    lastupdate date,
    updateby character varying(25),
    ownerid1 integer,
    owner_id integer
);


ALTER TABLE vetsystem.pet OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 17020)
-- Name: pet_petid_seq; Type: SEQUENCE; Schema: vetsystem; Owner: postgres
--

CREATE SEQUENCE vetsystem.pet_petid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE vetsystem.pet_petid_seq OWNER TO postgres;

--
-- TOC entry 2979 (class 0 OID 0)
-- Dependencies: 214
-- Name: pet_petid_seq; Type: SEQUENCE OWNED BY; Schema: vetsystem; Owner: postgres
--

ALTER SEQUENCE vetsystem.pet_petid_seq OWNED BY vetsystem.pet.petid;


--
-- TOC entry 224 (class 1259 OID 17255)
-- Name: test; Type: TABLE; Schema: vetsystem; Owner: postgres
--

CREATE TABLE vetsystem.test (
    companyid integer NOT NULL,
    name character varying(128) NOT NULL
);


ALTER TABLE vetsystem.test OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 17253)
-- Name: test_companyid_seq; Type: SEQUENCE; Schema: vetsystem; Owner: postgres
--

CREATE SEQUENCE vetsystem.test_companyid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE vetsystem.test_companyid_seq OWNER TO postgres;

--
-- TOC entry 2980 (class 0 OID 0)
-- Dependencies: 223
-- Name: test_companyid_seq; Type: SEQUENCE OWNED BY; Schema: vetsystem; Owner: postgres
--

ALTER SEQUENCE vetsystem.test_companyid_seq OWNED BY vetsystem.test.companyid;


--
-- TOC entry 211 (class 1259 OID 16992)
-- Name: vet; Type: TABLE; Schema: vetsystem; Owner: postgres
--

CREATE TABLE vetsystem.vet (
    vetid integer NOT NULL,
    employeeid integer,
    licenseid character varying(128) NOT NULL,
    licenseissuedby character varying(128) NOT NULL,
    licensetype character varying(128) NOT NULL,
    isactive character varying(1) DEFAULT 'A'::character varying NOT NULL,
    creationdate date DEFAULT now() NOT NULL,
    createdby character varying(25) DEFAULT USER NOT NULL,
    lastupdate date,
    updateby character varying(25),
    employee_id1 integer
);


ALTER TABLE vetsystem.vet OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16990)
-- Name: vet_vetid_seq; Type: SEQUENCE; Schema: vetsystem; Owner: postgres
--

CREATE SEQUENCE vetsystem.vet_vetid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE vetsystem.vet_vetid_seq OWNER TO postgres;

--
-- TOC entry 2982 (class 0 OID 0)
-- Dependencies: 210
-- Name: vet_vetid_seq; Type: SEQUENCE OWNED BY; Schema: vetsystem; Owner: postgres
--

ALTER SEQUENCE vetsystem.vet_vetid_seq OWNED BY vetsystem.vet.vetid;


--
-- TOC entry 2759 (class 2604 OID 16829)
-- Name: branch branchid; Type: DEFAULT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.branch ALTER COLUMN branchid SET DEFAULT nextval('vetsystem.branch_branchid_seq'::regclass);


--
-- TOC entry 2779 (class 2604 OID 17044)
-- Name: checkup checkupid; Type: DEFAULT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.checkup ALTER COLUMN checkupid SET DEFAULT nextval('vetsystem.checkup_checkupid_seq'::regclass);


--
-- TOC entry 2783 (class 2604 OID 17060)
-- Name: checkupreport checkupreportid; Type: DEFAULT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.checkupreport ALTER COLUMN checkupreportid SET DEFAULT nextval('vetsystem.checkupreport_checkupreportid_seq'::regclass);


--
-- TOC entry 2755 (class 2604 OID 16786)
-- Name: company companyid; Type: DEFAULT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.company ALTER COLUMN companyid SET DEFAULT nextval('vetsystem.company_comanyid_seq'::regclass);


--
-- TOC entry 2763 (class 2604 OID 16976)
-- Name: employee employeeid; Type: DEFAULT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.employee ALTER COLUMN employeeid SET DEFAULT nextval('vetsystem.employee_employeeid_seq'::regclass);


--
-- TOC entry 2771 (class 2604 OID 17011)
-- Name: owner ownerid; Type: DEFAULT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.owner ALTER COLUMN ownerid SET DEFAULT nextval('vetsystem.owner_ownerid_seq'::regclass);


--
-- TOC entry 2775 (class 2604 OID 17025)
-- Name: pet petid; Type: DEFAULT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.pet ALTER COLUMN petid SET DEFAULT nextval('vetsystem.pet_petid_seq'::regclass);


--
-- TOC entry 2787 (class 2604 OID 17258)
-- Name: test companyid; Type: DEFAULT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.test ALTER COLUMN companyid SET DEFAULT nextval('vetsystem.test_companyid_seq'::regclass);


--
-- TOC entry 2767 (class 2604 OID 16995)
-- Name: vet vetid; Type: DEFAULT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.vet ALTER COLUMN vetid SET DEFAULT nextval('vetsystem.vet_vetid_seq'::regclass);


--
-- TOC entry 2791 (class 2606 OID 16837)
-- Name: branch branch_pkey; Type: CONSTRAINT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.branch
    ADD CONSTRAINT branch_pkey PRIMARY KEY (branchid);


--
-- TOC entry 2801 (class 2606 OID 17049)
-- Name: checkup checkup_pkey; Type: CONSTRAINT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.checkup
    ADD CONSTRAINT checkup_pkey PRIMARY KEY (checkupid);


--
-- TOC entry 2803 (class 2606 OID 17068)
-- Name: checkupreport checkupreport_pkey; Type: CONSTRAINT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.checkupreport
    ADD CONSTRAINT checkupreport_pkey PRIMARY KEY (checkupreportid);


--
-- TOC entry 2789 (class 2606 OID 16794)
-- Name: company company_pkey; Type: CONSTRAINT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.company
    ADD CONSTRAINT company_pkey PRIMARY KEY (companyid);


--
-- TOC entry 2805 (class 2606 OID 17245)
-- Name: databasechangeloglock databasechangeloglock_pkey; Type: CONSTRAINT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);


--
-- TOC entry 2793 (class 2606 OID 16984)
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (employeeid);


--
-- TOC entry 2797 (class 2606 OID 17019)
-- Name: owner owner_pkey; Type: CONSTRAINT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.owner
    ADD CONSTRAINT owner_pkey PRIMARY KEY (ownerid);


--
-- TOC entry 2799 (class 2606 OID 17033)
-- Name: pet pet_pkey; Type: CONSTRAINT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.pet
    ADD CONSTRAINT pet_pkey PRIMARY KEY (petid);


--
-- TOC entry 2807 (class 2606 OID 17260)
-- Name: test test_pkey; Type: CONSTRAINT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.test
    ADD CONSTRAINT test_pkey PRIMARY KEY (companyid);


--
-- TOC entry 2795 (class 2606 OID 17000)
-- Name: vet vet_pkey; Type: CONSTRAINT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.vet
    ADD CONSTRAINT vet_pkey PRIMARY KEY (vetid);


--
-- TOC entry 2808 (class 2606 OID 16838)
-- Name: branch branch_comanyid_fkey; Type: FK CONSTRAINT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.branch
    ADD CONSTRAINT branch_comanyid_fkey FOREIGN KEY (companyid) REFERENCES vetsystem.company(companyid);


--
-- TOC entry 2813 (class 2606 OID 17050)
-- Name: checkup checkup_vetid_fkey; Type: FK CONSTRAINT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.checkup
    ADD CONSTRAINT checkup_vetid_fkey FOREIGN KEY (vetid) REFERENCES vetsystem.vet(vetid);


--
-- TOC entry 2814 (class 2606 OID 17069)
-- Name: checkupreport checkupreport_checkupid_fkey; Type: FK CONSTRAINT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.checkupreport
    ADD CONSTRAINT checkupreport_checkupid_fkey FOREIGN KEY (checkupid) REFERENCES vetsystem.checkup(checkupid);


--
-- TOC entry 2815 (class 2606 OID 17074)
-- Name: checkupreport checkupreport_petid_fkey; Type: FK CONSTRAINT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.checkupreport
    ADD CONSTRAINT checkupreport_petid_fkey FOREIGN KEY (petid) REFERENCES vetsystem.pet(petid);


--
-- TOC entry 2809 (class 2606 OID 16985)
-- Name: employee employee_branchid_fkey; Type: FK CONSTRAINT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.employee
    ADD CONSTRAINT employee_branchid_fkey FOREIGN KEY (branchid) REFERENCES vetsystem.branch(branchid);


--
-- TOC entry 2812 (class 2606 OID 17194)
-- Name: pet fk7qfti9yba86tgfe9oobeqxfxg; Type: FK CONSTRAINT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.pet
    ADD CONSTRAINT fk7qfti9yba86tgfe9oobeqxfxg FOREIGN KEY (owner_id) REFERENCES vetsystem.owner(ownerid);


--
-- TOC entry 2816 (class 2606 OID 17167)
-- Name: checkupreport fkocgev1frg2ihabdrk57cmtu6; Type: FK CONSTRAINT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.checkupreport
    ADD CONSTRAINT fkocgev1frg2ihabdrk57cmtu6 FOREIGN KEY (pet_id) REFERENCES vetsystem.pet(petid);


--
-- TOC entry 2811 (class 2606 OID 17034)
-- Name: pet pet_ownerid_fkey; Type: FK CONSTRAINT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.pet
    ADD CONSTRAINT pet_ownerid_fkey FOREIGN KEY (ownerid) REFERENCES vetsystem.owner(ownerid);


--
-- TOC entry 2810 (class 2606 OID 17001)
-- Name: vet vet_employeeid_fkey; Type: FK CONSTRAINT; Schema: vetsystem; Owner: postgres
--

ALTER TABLE ONLY vetsystem.vet
    ADD CONSTRAINT vet_employeeid_fkey FOREIGN KEY (employeeid) REFERENCES vetsystem.employee(employeeid);


--
-- TOC entry 2966 (class 0 OID 0)
-- Dependencies: 207
-- Name: TABLE branch; Type: ACL; Schema: vetsystem; Owner: postgres
--

GRANT SELECT ON TABLE vetsystem.branch TO PUBLIC;


--
-- TOC entry 2968 (class 0 OID 0)
-- Dependencies: 217
-- Name: TABLE checkup; Type: ACL; Schema: vetsystem; Owner: postgres
--

GRANT SELECT ON TABLE vetsystem.checkup TO PUBLIC;


--
-- TOC entry 2970 (class 0 OID 0)
-- Dependencies: 219
-- Name: TABLE checkupreport; Type: ACL; Schema: vetsystem; Owner: postgres
--

GRANT SELECT ON TABLE vetsystem.checkupreport TO PUBLIC;


--
-- TOC entry 2972 (class 0 OID 0)
-- Dependencies: 205
-- Name: TABLE company; Type: ACL; Schema: vetsystem; Owner: postgres
--

GRANT SELECT ON TABLE vetsystem.company TO PUBLIC;


--
-- TOC entry 2974 (class 0 OID 0)
-- Dependencies: 209
-- Name: TABLE employee; Type: ACL; Schema: vetsystem; Owner: postgres
--

GRANT SELECT ON TABLE vetsystem.employee TO PUBLIC;


--
-- TOC entry 2976 (class 0 OID 0)
-- Dependencies: 213
-- Name: TABLE owner; Type: ACL; Schema: vetsystem; Owner: postgres
--

GRANT SELECT ON TABLE vetsystem.owner TO PUBLIC;


--
-- TOC entry 2978 (class 0 OID 0)
-- Dependencies: 215
-- Name: TABLE pet; Type: ACL; Schema: vetsystem; Owner: postgres
--

GRANT SELECT ON TABLE vetsystem.pet TO PUBLIC;


--
-- TOC entry 2981 (class 0 OID 0)
-- Dependencies: 211
-- Name: TABLE vet; Type: ACL; Schema: vetsystem; Owner: postgres
--

GRANT SELECT ON TABLE vetsystem.vet TO PUBLIC;


-- Completed on 2019-10-10 21:07:48

--
-- PostgreSQL database dump complete
--


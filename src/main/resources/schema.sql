CREATE TABLE products (
    user_id integer NOT NULL,
    name character varying(255),
    price real,
    date date,
    product_id integer NOT NULL
);

CREATE TABLE users (
    name character varying(15) NOT NULL,
    surname character varying(15),
    email character varying(130) NOT NULL,
    id integer NOT NULL
);

CREATE SEQUENCE products_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.products OWNER TO postgres;

ALTER TABLE public.products_product_id_seq OWNER TO postgres;

ALTER SEQUENCE products_product_id_seq OWNED BY products.product_id;

ALTER TABLE public.users OWNER TO postgres;

ALTER TABLE public.users_id_seq OWNER TO postgres;

ALTER SEQUENCE users_id_seq OWNED BY users.id;

ALTER TABLE ONLY products ALTER COLUMN product_id SET DEFAULT 
nextval('products_product_id_seq'::regclass);

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);
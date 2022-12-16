
CREATE TABLE IF NOT EXISTS public.city
(
    id               BIGSERIAL,
    name             VARCHAR(100) NOT NULL,
    photo            VARCHAR(3000) NOT NULL,
    PRIMARY KEY (id)
);
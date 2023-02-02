-- liquibase formatted sql

-- changeset bbazarov:1
CREATE TABLE shelter_user
(
    "shelter_user_id"             SERIAL,
    "shelter_user_chat_id"        bigint,
    "shelter_user_name"           character varying(255),
    "shelter_user_age"            bigint,
    "shelter_user_address"        character varying(255),
    "shelter_user_phone"          character varying(255),
    "shelter_user_able_adopt_pet" boolean,
    PRIMARY KEY (shelter_user_id)
);

CREATE TABLE shelter_pet
(
    "shelter_pet_id"           SERIAL,
    "shelter_pet_kind"         character varying(255),
    "shelter_pet_breed"        character varying(255),
    "shelter_pet_color"        character varying(255),
    "shelter_pet_name"         character varying(255),
    "shelter_pet_age"          bigint,
    "shelter_pet_reciept_date" timestamp
);
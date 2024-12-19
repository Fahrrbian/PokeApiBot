ALTER TABLE first_evo_pokemon ADD COLUMN evolution_level INT NOT NULL DEFAULT 0;
ALTER TABLE first_evo_pokemon ADD COLUMN next_evolution_name VARCHAR(255) NOT NULL DEFAULT '';

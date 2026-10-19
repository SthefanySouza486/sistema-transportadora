ALTER TABLE despesas DROP CONSTRAINT IF EXISTS despesas_categoria_check;
ALTER TABLE despesas ADD CONSTRAINT despesas_categoria_check CHECK (categoria IN ('COMBUSTIVEL', 'MECANICA', 'PEDAGIO', 'MANUTENCAO', 'ALIMENTACAO', 'OUTROS'));

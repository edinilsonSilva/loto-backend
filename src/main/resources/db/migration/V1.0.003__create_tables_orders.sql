-- Criação da tabela de Pedidos
CREATE TABLE orders
(
    id             SERIAL PRIMARY KEY,
    created_at     timestamp(30) NULL,
    updated_at     timestamp(30) NULL,
    account_id     INTEGER       NOT NULL REFERENCES accounts (id),
    status         VARCHAR(50)   NOT NULL,
    payment_method VARCHAR(50),
    transaction_id VARCHAR(255),
    charge_id      INTEGER,
    amount         VARCHAR(255),
    CONSTRAINT order_status_check CHECK (status IN ('PENDING', 'PAID', 'CANCELED', 'SHIPPED', 'DELIVERED'))
);

-- Criação da tabela de Itens do Pedido
CREATE TABLE order_items
(
    id        SERIAL PRIMARY KEY,
    order_id  INTEGER NOT NULL REFERENCES orders (id) ON DELETE CASCADE,
    pool_id INTEGER NOT NULL REFERENCES pools (id)
);

-- Índices e otimizações
CREATE INDEX idx_order_items_order_id ON order_items (order_id);
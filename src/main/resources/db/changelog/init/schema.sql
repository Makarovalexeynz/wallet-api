CREATE TABLE IF NOT EXISTS wallets (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    balance DECIMAL(19,2) NOT NULL CHECK (balance >= 0),
    version BIGINT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS wallet_transactions (
id BIGSERIAL PRIMARY KEY,
    wallet_id UUID NOT NULL,
    operation_type VARCHAR(10) NOT NULL,
    amount DECIMAL(19,2) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    version BIGINT NOT NULL
);



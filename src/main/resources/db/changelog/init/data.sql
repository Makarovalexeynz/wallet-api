INSERT INTO wallets (id, balance, version) VALUES
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 1000.00, 1),
    ('b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 500.50, 1),
    ('c2eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 750.25, 1);

INSERT INTO wallet_transactions (wallet_id, operation_type, amount, created_at, version) VALUES
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'DEPOSIT', 500.00, '2023-01-01 10:00:00', 1),
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'DEPOSIT', 500.00, '2023-01-02 11:00:00', 1),
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'WITHDRAW', 200.00, '2023-01-03 12:00:00', 1),
    ('b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'DEPOSIT', 1000.00, '2023-01-01 09:00:00', 1),
    ('b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'WITHDRAW', 499.50, '2023-01-02 10:00:00', 1),
    ('c2eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'DEPOSIT', 750.25, '2023-01-01 08:00:00', 1);
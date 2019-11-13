INSERT INTO `users`
    (`account_number`, `name`, `created_at`)
VALUES
    ('13517115', 'Edward Alexander Jaya', now()),
    ('13517070', 'Aidil Rezjki Suljztan Syawaludin', now()),
    ('13516088', 'Tanor Abraham Reyuko', now()),
    ('13517000', 'Engima', now());

INSERT INTO `transactions`
    (`src_account_number`, `dest_account_number`, `dest_virtual_account`, `amount`, `created_at`)
VALUES
    ('13517115', '13517000', '13517000001', 10000, now()),
    ('13517115', '13517000', '13517000002', 12000, now()),
    ('13517115', '13517000', '13517000003', 11222, now()),
    ('13517000', '13517115', '13517115001', 11111, now()),
    ('13517000', '13517115', '13517115002', 10101, now()),
    ('13517000', '13517115', '13517115003', 12000, now());

INSERT INTO `virtual_accounts`
    (`virtual_account_number`, `account_number`, `created_at`)
VALUES
    ('13517000001', '13517000', now()),
    ('13517000002', '13517000', now()),
    ('13517000003', '13517000', now()),
    ('13517115001', '13517115', now()),
    ('13517115002', '13517115', now()),
    ('13517115003', '13517115', now());
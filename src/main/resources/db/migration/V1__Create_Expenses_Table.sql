create table if not exists dev.expenses
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    expenseDate TIMESTAMP NOT NULL,
    expenseType TEXT,
    payee TEXT,
    description TEXT,
    amount TEXT,
    date_created TIMESTAMP NOT NULL DEFAULT now(),
    date_modified TIMESTAMP NOT NULL DEFAULT now()
);

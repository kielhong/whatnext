INSERT INTO category (id, name, color) VALUES (1, 'home', 'e83e8c');

INSERT INTO task (description, priority, status, category_id, created_date) VALUES ('Do it now', 1, 1, 1, now());
INSERT INTO task (description, priority, status, category_id, created_date) VALUES ('Do it again', 1, 1, 1, now());
INSERT INTO task (description, priority, status, category_id, created_date) VALUES ('Not now', 1, 1, 1, now());
INSERT INTO task (description, priority, status, category_id, created_date) VALUES ('Maybe later', 1, 1, 1, now());
CREATE TABLE IF NOT EXISTS content (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(20) NOT NULL,
    content_type VARCHAR(50) NOT NULL,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP,
    url VARCHAR(255)
    );

-- INSERT INTO content(title, description, status, content_type, date_created)
-- VALUES ('My First Spring Post', 'A Post About Spring', 'IDEA', 'ARTICLE', CURRENT_TIMESTAMP)
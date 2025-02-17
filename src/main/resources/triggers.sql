DELIMITER $$

CREATE TRIGGER trg_product_insert_audit
AFTER INSERT ON products
FOR EACH ROW
BEGIN
    INSERT INTO product_audit (product_id, action, action_timestamp, performed_by)
    VALUES (NEW.id, 'INSERT', NOW(), CURRENT_USER());
END$$

DELIMITER ;




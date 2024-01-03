import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Button, Col, Form, Row } from 'reactstrap';
import { ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ExcelImport = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const loading = useAppSelector(state => state.document.loading);
  const updating = useAppSelector(state => state.document.updating);
  const updateSuccess = useAppSelector(state => state.document.updateSuccess);
  const [documentEntity, setDocumentEntity] = useState({
    file: null,
  });

  const handleClose = () => {
    navigate('/document');
  };

  useEffect(() => {
    console.log(dispatch);
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  // eslint-disable-next-line complexity
  const saveEntity = values => {
    const entity = {
      ...documentEntity,
      ...values,
    };
    console.log(entity);
  };
  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="dispatchApplicationApp.document.home.createOrEditLabel" data-cy="DocumentCreateUpdateHeading">
            Excel Import
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <Form onSubmit={saveEntity}>
              <ValidatedBlobField type={'file'} label="File" id="document-file" name="file" data-cy="file" />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/excel-data" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </Form>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default ExcelImport;

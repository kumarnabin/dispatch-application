import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './document.reducer';

export const DocumentDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const documentEntity = useAppSelector(state => state.document.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="documentDetailsHeading">
          <Translate contentKey="dispatchApplicationApp.document.detail.title">Document</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{documentEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="dispatchApplicationApp.document.name">Name</Translate>
            </span>
          </dt>
          <dd>{documentEntity.name}</dd>
          <dt>
            <span id="file">
              <Translate contentKey="dispatchApplicationApp.document.file">File</Translate>
            </span>
          </dt>
          <dd>
            {documentEntity.file ? (
              <div>
                {documentEntity.fileContentType ? (
                  <a onClick={openFile(documentEntity.fileContentType, documentEntity.file)}>
                    <img src={`data:${documentEntity.fileContentType};base64,${documentEntity.file}`} style={{ maxHeight: '30px' }} />
                  </a>
                ) : null}
                <span>
                  {documentEntity.fileContentType}, {byteSize(documentEntity.file)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="dispatchApplicationApp.document.employee">Employee</Translate>
          </dt>
          <dd>{documentEntity.employee ? documentEntity.employee.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/document" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/document/${documentEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DocumentDetail;

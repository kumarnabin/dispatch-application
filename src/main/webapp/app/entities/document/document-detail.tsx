import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
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
        <h2 data-cy="documentDetailsHeading">Document</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{documentEntity.id}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{documentEntity.name}</dd>
          <dt>
            <span id="file">File</span>
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
            <span id="publicationDate">Publication Date</span>
          </dt>
          <dd>
            {documentEntity.publicationDate ? (
              <TextFormat value={documentEntity.publicationDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>Employee</dt>
          <dd>{documentEntity.employee ? documentEntity.employee.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/document" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/document/${documentEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default DocumentDetail;

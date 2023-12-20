import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './branch.reducer';

export const BranchDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const branchEntity = useAppSelector(state => state.branch.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="branchDetailsHeading">Branch</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{branchEntity.id}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{branchEntity.name}</dd>
          <dt>
            <span id="publicationDate">Publication Date</span>
          </dt>
          <dd>
            {branchEntity.publicationDate ? <TextFormat value={branchEntity.publicationDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>Service Provider</dt>
          <dd>{branchEntity.serviceProvider ? branchEntity.serviceProvider.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/branch" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/branch/${branchEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default BranchDetail;

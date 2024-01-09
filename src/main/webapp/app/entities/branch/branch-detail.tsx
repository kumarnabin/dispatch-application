import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

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
        <h2 data-cy="branchDetailsHeading">
          <Translate contentKey="dispatchApplicationApp.branch.detail.title">Branch</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{branchEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="dispatchApplicationApp.branch.name">Name</Translate>
            </span>
          </dt>
          <dd>{branchEntity.name}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="dispatchApplicationApp.branch.code">Code</Translate>
            </span>
          </dt>
          <dd>{branchEntity.code}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="dispatchApplicationApp.branch.status">Status</Translate>
            </span>
          </dt>
          <dd>{branchEntity.status}</dd>
          <dt>
            <Translate contentKey="dispatchApplicationApp.branch.serviceProvider">Service Provider</Translate>
          </dt>
          <dd>{branchEntity.serviceProvider ? branchEntity.serviceProvider.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/branch" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/branch/${branchEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default BranchDetail;

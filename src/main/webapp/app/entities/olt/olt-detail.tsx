import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './olt.reducer';

export const OltDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const oltEntity = useAppSelector(state => state.olt.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="oltDetailsHeading">
          <Translate contentKey="dispatchApplicationApp.olt.detail.title">Olt</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{oltEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="dispatchApplicationApp.olt.name">Name</Translate>
            </span>
          </dt>
          <dd>{oltEntity.name}</dd>
          <dt>
            <span id="detail">
              <Translate contentKey="dispatchApplicationApp.olt.detail">Detail</Translate>
            </span>
          </dt>
          <dd>{oltEntity.detail}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="dispatchApplicationApp.olt.status">Status</Translate>
            </span>
          </dt>
          <dd>{oltEntity.status}</dd>
          <dt>
            <Translate contentKey="dispatchApplicationApp.olt.branch">Branch</Translate>
          </dt>
          <dd>{oltEntity.branch ? oltEntity.branch.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/olt" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/olt/${oltEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default OltDetail;

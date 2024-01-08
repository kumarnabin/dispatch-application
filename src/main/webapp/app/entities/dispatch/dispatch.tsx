import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './dispatch.reducer';

export const Dispatch = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const dispatchList = useAppSelector(state => state.dispatch.entities);
  const loading = useAppSelector(state => state.dispatch.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="dispatch-heading" data-cy="DispatchHeading">
        Dispatches
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh list
          </Button>
          <Link to="/dispatch/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Dispatch
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {dispatchList && dispatchList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  ID <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('voice')}>
                  Voice <FontAwesomeIcon icon={getSortIconByFieldName('voice')} />
                </th>
                <th className="hand" onClick={sort('data')}>
                  Data <FontAwesomeIcon icon={getSortIconByFieldName('data')} />
                </th>
                <th className="hand" onClick={sort('iptv')}>
                  Iptv <FontAwesomeIcon icon={getSortIconByFieldName('iptv')} />
                </th>
                <th className="hand" onClick={sort('customerName')}>
                  Customer Name <FontAwesomeIcon icon={getSortIconByFieldName('customerName')} />
                </th>
                <th className="hand" onClick={sort('contactNo')}>
                  Contact No <FontAwesomeIcon icon={getSortIconByFieldName('contactNo')} />
                </th>
                <th className="hand" onClick={sort('oltPort')}>
                  Olt Port <FontAwesomeIcon icon={getSortIconByFieldName('oltPort')} />
                </th>
                <th className="hand" onClick={sort('regDate')}>
                  Reg Date <FontAwesomeIcon icon={getSortIconByFieldName('regDate')} />
                </th>
                <th className="hand" onClick={sort('fapPort')}>
                  Fap Port <FontAwesomeIcon icon={getSortIconByFieldName('fapPort')} />
                </th>
                <th className="hand" onClick={sort('cpeSn')}>
                  Cpe Sn <FontAwesomeIcon icon={getSortIconByFieldName('cpeSn')} />
                </th>
                <th className="hand" onClick={sort('cpeRx')}>
                  Cpe Rx <FontAwesomeIcon icon={getSortIconByFieldName('cpeRx')} />
                </th>
                <th className="hand" onClick={sort('complain')}>
                  Complain <FontAwesomeIcon icon={getSortIconByFieldName('complain')} />
                </th>
                <th className="hand" onClick={sort('remark')}>
                  Remark <FontAwesomeIcon icon={getSortIconByFieldName('remark')} />
                </th>
                <th className="hand" onClick={sort('status')}>
                  Status <FontAwesomeIcon icon={getSortIconByFieldName('status')} />
                </th>
                <th className="hand" onClick={sort('location')}>
                  Location <FontAwesomeIcon icon={getSortIconByFieldName('location')} />
                </th>
                <th className="hand" onClick={sort('printDate')}>
                  Print Date <FontAwesomeIcon icon={getSortIconByFieldName('printDate')} />
                </th>
                <th className="hand" onClick={sort('publicationDate')}>
                  Publication Date <FontAwesomeIcon icon={getSortIconByFieldName('publicationDate')} />
                </th>
                <th>
                  Team <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dispatchList.map((dispatch, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/dispatch/${dispatch.id}`} color="link" size="sm">
                      {dispatch.id}
                    </Button>
                  </td>
                  <td>{dispatch.voice}</td>
                  <td>{dispatch.data}</td>
                  <td>{dispatch.iptv}</td>
                  <td>{dispatch.customerName}</td>
                  <td>{dispatch.contactNo}</td>
                  <td>{dispatch.oltPort}</td>
                  <td>{dispatch.regDate}</td>
                  <td>{dispatch.fapPort}</td>
                  <td>{dispatch.cpeSn}</td>
                  <td>{dispatch.cpeRx}</td>
                  <td>{dispatch.complain}</td>
                  <td>{dispatch.remark}</td>
                  <td>{dispatch.status}</td>
                  <td>{dispatch.location}</td>
                  <td>
                    {dispatch.printDate ? <TextFormat type="date" value={dispatch.printDate} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {dispatch.publicationDate ? <TextFormat type="date" value={dispatch.publicationDate} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{dispatch.team ? <Link to={`/team/${dispatch.team.id}`}>{dispatch.team.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/dispatch/${dispatch.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`/dispatch/${dispatch.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/dispatch/${dispatch.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Dispatches found</div>
        )}
      </div>
    </div>
  );
};

export default Dispatch;

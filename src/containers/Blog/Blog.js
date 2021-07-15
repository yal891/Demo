import React, { Component } from 'react';
import NewPost from './NewPost/NewPost';
import { Route, NavLink, Switch, Redirect } from 'react-router-dom';

class Blog extends Component{

    render() {
        return (
            < div className = 'Blog' >
                <h1>Blog Directions</h1>

                <nav>
                    <ul>
                        <li><NavLink
                            to="/posts/"
                            exact
                            activeClassName="active"
                            activeStyle={{
                                color: '#fa923f',
                                textDecoration: 'underline'
                            }}>Posts</NavLink></li>
                        <li><NavLink to={{
                            pathname: '/new-post',
                            hash: '#submit',
                            search: '?quick-submit=true'
                        }}>New Post</NavLink></li>
                    </ul>
                </nav>
                <Switch>
                    {<Route path="/new-post" component={NewPost} />}
                    <Route render={() => <h1>Click to get posts</h1>}/>
                </Switch>
            </div>
        );
    }
}
export default Blog

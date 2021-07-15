import React from 'react' ;
import axios from 'axios' ;

class NewPost extends React . Component  {
    state = {
        id:  '' ,
        author: '' ,
        body : '' ,
        data : []
    }

    componentDidMount() {
        axios.get( 'https://jsonplaceholder.typicode.com/posts' )
            .then(res => {
                let newData = res.data.slice(0,3);
                this.setState({
                    id: newData[newData.length - 1 ].id + 1 ,
                    data: newData
                }, () => console.log(this.state.id))
                console.log(newData)
            })
            .catch(err => console .log("The error is:" + err))
    }

    render() {
        return (
            < div className = 'Axios Practice' >
                <h1>Blog</h1>
                <div className='CreateBlog'>
                    <b>id of blog: </b>
                    <input type='number' value={this.state.id} />
                    <form>
              <textarea placeholder='Enter Body' value={this.state.body}>
          </textarea>
                        <input type='submit' value='New Blog'/>
                    </form>
                </div>
                {
                    this.state.data.map((post, index) => (
                        <article key={index}>
                            <h2>{index + 1}.{post.title}</h2>
                            <p>{post.body.substr(0, 99)}...</p>
                            <button className='delete'>Delete</button>
                        </article>
                    ))
                }
            </div>
        )
    }
}
export default NewPost;